import React, { useState } from 'react';
import styled from 'styled-components';

const LoginComponent = () => {
  const [justifyActive, setJustifyActive] = useState('tab1');
  const [loginUsername, setLoginUsername] = useState('');
  const [loginPassword, setLoginPassword] = useState('');
  const [signupUsername, setSignupUsername] = useState('');
  const [signupPassword, setSignupPassword] = useState('');
  const [email, setEmail] = useState('');

  const handleJustifyClick = (value) => {
    if (value === justifyActive) {
      return;
    }
    setJustifyActive(value);
  };

  const handleLogin = () => {
    // console.log('Logging in with username:', loginUsername, 'and password:', loginPassword);
  };

  const handleSignup = () => {
    // console.log('Signing up with username:', signupUsername, 'password:', signupPassword, 'and email:', email);
  };

  return (
    <LoginStyled>
      <div className="tabs">
        <button className={justifyActive === 'tab1' ? 'active' : ''} onClick={() => handleJustifyClick('tab1')}>
          Login
        </button>
        <button className={justifyActive === 'tab2' ? 'active' : ''} onClick={() => handleJustifyClick('tab2')}>
          Sign Up
        </button>
      </div>
      <div className="form-container">
        {justifyActive === 'tab1' ? (
          <div>
            <h2>Login</h2>
            <input
              type="email"
              placeholder="Email"
              value={loginUsername}
              onChange={(e) => setLoginUsername(e.target.value)}
            />
            <input
              type="password"
              placeholder="Password"
              value={loginPassword}
              onChange={(e) => setLoginPassword(e.target.value)}
            />
            <button className='signLog' onClick={handleLogin}>Login</button>
          </div>
        ) : (
          <div>
            <h2 style={{ padding: "10px" }}>Sign Up</h2>

            <input
            type="email"
            placeholder="Email"
              
              value={signupUsername}
              onChange={(e) => setSignupUsername(e.target.value)}
            />
            <input
              type="password"
              placeholder="Password"
              value={signupPassword}
              onChange={(e) => setSignupPassword(e.target.value)}
            />
            <input
              type="text"
              placeholder="Username"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <button className='signLog' onClick={handleSignup}>Sign Up</button>
          </div>
        )}
      </div>
    </LoginStyled>
  );
};

const LoginStyled = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  padding-top:90px;

  .tabs {
    display: flex;
    min-width: 400px;
    gap: 10px;
    padding: 20px;
  }

  .tabs button {
    padding: 10px 20px;
    background-color: transparent;
    border: none;
    cursor: pointer;
    font-size: 25px;
    color: #333;
    transition: all 0.3s ease;
  }

  .tabs button.active {
    color: #fff;
    background-color: #F56692;
    border-radius: 5px;
  }

  .form-container {
    width: 100%;
    max-width: 600px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  input[type='text'],
  input[type='password'],
  input[type='email'],
  button {
    width: 100%;
    padding: 20px;
    margin-top: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    outline: none;
    font-size: 20px;
  }

  .signLog{
    box-shadow: 0px 1px 15px rgba(0, 0, 0, 0.06);
    color: #FFFFFF;
    background-color: #F56692;
    cursor: pointer;
    &:hover{
        background: var(--color-green) !important;
    }
}
  button:hover {
    background-color: #f299b4;
  }
`;

export default LoginComponent;
