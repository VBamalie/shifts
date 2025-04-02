todo:
- [x] seeded employees don't have emails or passwords, figure out why it doesn't like those(sort of fixed. it's the seeding that's the problem but I can just use the seeding for display prototyping and then use the actual data from the database for the actual app)
- [x] add the manager boolean to the employee table
- [x] create the auth for the backend
- [x] create the auth for the frontend
- [x] add another calendar seed
- [x] create the context that a logged in user can see their calendar info
- [x] research front end date mechanics to decide if we should change the date types on shift and time off request tables
- [1/2] create the skeleton for the calendar
- [x] create the component where all employees are listed
- [x] make the dashboard only show this week's shift info
- [x] create buttons that only a manager can see
- [ ] create pages for editing calendar info
- [ ] create pages for editing employee info
- [ ] create pages for editing shift info
- [x] create new calendar page
- [x] create pagination for other week information
- [ ] create functionality for autocreate
- [ ] make things prettier
- [ ]vercel deploy?

## Front End Dependencies:
- react-router-dom
- MUI X (https://mui.com/material-ui/getting-started/)
- Dayjs
- Axios

## Back End Dependencies:
- Springboot
- Spring Data JPA
- Spring Security
- H2 Database