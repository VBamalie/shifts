// Import React and necessary hooks
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate hook

function LoginPage() {
    return(
        <div className="login-wrapper">
        <h1>Please Log In</h1>
        <form>
          <label>
            <p>Email</p>
            <input type="text" />
          </label>
          <label>
            <p>Password</p>
            <input type="password" />
          </label>
          <div>
            <button type="submit">Submit</button>
          </div>
        </form>
        </div>
      )
}

export default LoginPage;
