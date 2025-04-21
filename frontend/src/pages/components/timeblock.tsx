//this is the component to add, edit, and remove timeblocks.
import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/DeleteOutlined';
import SaveIcon from '@mui/icons-material/Save';
import CancelIcon from '@mui/icons-material/Close';
import {
  GridRowsProp,
  GridRowModesModel,
  GridRowModes,
  DataGrid,
  GridColDef,
  GridToolbarContainer,
  GridActionsCellItem,
  GridEventListener,
  GridRowId,
  GridRowModel,
  GridRowEditStopReasons,
  GridSlotProps,
} from '@mui/x-data-grid';
import axiosInstance from '../../axiosConfig';
import { useAuth } from './AuthContext';
//this uses a MUI X Data Grid to display the timeblocks.
//it also uses a MUI X Data Grid Toolbar to add, edit, and delete timeblocks.
//Documentation found at https://mui.com/x/react-data-grid/

declare module '@mui/x-data-grid' {
  interface ToolbarPropsOverrides {
    setRows: (newRows: (oldRows: GridRowsProp) => GridRowsProp) => void;
    setRowModesModel: (
      newModel: (oldModel: GridRowModesModel) => GridRowModesModel,
    ) => void;
  }
}

function EditToolbar(props: GridSlotProps['toolbar']) {
  const {employee} = useAuth();
  const { setRows, setRowModesModel, setRefreshData } = props;

  const handleClick = () => {//this is for adding a new timeblock
     axiosInstance.post(`http://localhost:8080/api/timeblock/${employee?.calendar}`, {}).then((response)=>{
      setRefreshData((prev) => prev + 1);
    },100).catch((error)=>{
        console.log("error creating timeblock")
    })
  };

  return (
    <GridToolbarContainer>
      <Button color="primary" startIcon={<AddIcon />} onClick={handleClick}>
        Add record
      </Button>
    </GridToolbarContainer>
  );
}

export default function TimeBlock() {
  const [rows, setRows] = React.useState<GridRowModel[]>([]);
  const [rowModesModel, setRowModesModel] = React.useState<GridRowModesModel>({});
  const {employee} = useAuth();
  const [refreshData, setRefreshData] = React.useState(0);
  React.useEffect(()=>{
    axiosInstance.get(`http://localhost:8080/api/timeblock/calendar/${employee?.calendar}`).then((response)=>{
        setRows(response.data);
    }).catch((error)=>{
        console.log("error fetching timeblocks")
    })
  }, [refreshData, employee]);

  const handleRowEditStop: GridEventListener<'rowEditStop'> = (params, event) => {
    if (params.reason === GridRowEditStopReasons.rowFocusOut) {
      event.defaultMuiPrevented = true;
    }
  };

  const handleEditClick = (id: GridRowId) => () => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.Edit } });
  };

  const handleSaveClick = (id: GridRowId) => () => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.View } });
  };

  const handleDeleteClick = (id: GridRowId) => () => {//handles deleting a timeblock
    rows.map((row) => row.id !== id ? 
      axiosInstance.delete(`http://localhost:8080/api/timeblock/${id}`).then((response)=>{
        console.log('deleted timeblock', response.data);
        setRows(rows.filter((row) => row.id !== id));
      }).catch((error)=>{
        console.log("error deleting timeblocks")
      })
      : null
    )  };

  const handleCancelClick = (id: GridRowId) => () => {//handles canceling an edit
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View, ignoreModifications: true },
    });

    const editedRow = rows.find((row) => row.id === id);
    if (editedRow!.isNew) {
      setRows(rows.filter((row) => row.id !== id));
    }
  };

  const processRowUpdate = (newRow: GridRowModel) => {//handles saving an edit
    const updatedRow = { ...newRow, isNew: false };
    rows.map((row) => (row.id === newRow.id?
      axiosInstance.put(`http://localhost:8080/api/timeblock/${newRow.id}`, newRow).then((response)=>{
        console.log('updated timeblock', response.data);
    }).catch((error)=>{
        console.log("error updating timeblocks")
    })
    :
    updatedRow 
  ));
    return updatedRow;
  };

  const handleRowModesModelChange = (newRowModesModel: GridRowModesModel) => {//this is the columns for each row
    setRowModesModel(newRowModesModel);
  };

  const columns: GridColDef[] = [
    { field: 'weekDayEnum', 
    headerName: 'Day',
    width: 180, 
    editable: true,
    type: 'singleSelect',
    valueOptions: ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']
 },
    {
      field: 'startTime',
      headerName: 'Start Time',
      type: 'number',
      width: 180,
      align: 'left',
      headerAlign: 'left',
      editable: true,
    },
    {
      field: 'endTime',
      headerName: 'End Time',
      type: 'number',
      width: 180,
      align:'left',
      headerAlign: 'left',
      editable: true,
    },
    {
      field: 'shiftsRequired',
      headerName: 'Shifts Required',
      type: 'number',
      width: 180,
      editable: true,
      align: 'left',
      headerAlign: 'left'
    },
    {
      field: 'actions',
      type: 'actions',
      headerName: 'Actions',
      width: 100,
      cellClassName: 'actions',
      getActions: ({ id }) => {
        const isInEditMode = rowModesModel[id]?.mode === GridRowModes.Edit;

        if (isInEditMode) {
          return [
            <GridActionsCellItem
              icon={<SaveIcon />}
              label="Save"
              sx={{
                color: 'primary.main',
              }}
              onClick={handleSaveClick(id)}
            />,
            <GridActionsCellItem
              icon={<CancelIcon />}
              label="Cancel"
              className="textPrimary"
              onClick={handleCancelClick(id)}
              color="inherit"
            />,
          ];
        }

        return [
          <GridActionsCellItem
            icon={<EditIcon />}
            label="Edit"
            className="textPrimary"
            onClick={handleEditClick(id)}
            color="inherit"
          />,
          <GridActionsCellItem
            icon={<DeleteIcon />}
            label="Delete"
            onClick={handleDeleteClick(id)}
            color="inherit"
          />,
        ];
      },
    },
  ];

  return (
    <Box
      sx={{
        height: 500,
        width: '100%',
        '& .actions': {
          color: 'text.secondary',
        },
        '& .textPrimary': {
          color: 'text.primary',
        },
      }}
    >
      <DataGrid
        rows={rows}
        columns={columns}
        editMode="row"
        rowModesModel={rowModesModel}
        onRowModesModelChange={handleRowModesModelChange}
        onRowEditStop={handleRowEditStop}
        processRowUpdate={processRowUpdate}
        slots={{ toolbar: (props)=>(
          <EditToolbar {...props} setRefreshData={setRefreshData}/>
        ) }}        
      />
    </Box>
  );
}