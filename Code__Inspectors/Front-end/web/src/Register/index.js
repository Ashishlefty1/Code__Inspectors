import React, { useState } from 'react'
import { ToastContainer, toast } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import './Register.css'
import { Navbar } from 'react-bootstrap'
import NavBar from '../NavBar'
import { Link } from 'react-router-dom'
import ajax from '../Services/fetchService'

const Register = () => {
  const [registerData, setRegisterData] = useState({
    name: '',
    email: '',
    username: '',
    password: '',
    role: '',
  })

  const roles = ['ROLE_STUDENT', 'ROLE_CODE_REVIEWER']

  const changeHandle = (event) => {
    const { name, value } = event.target
    setRegisterData({ ...registerData, [name]: value })
  }

  const addData = () => {
    if (
      !registerData.name ||
      !registerData.email ||
      !registerData.username ||
      !registerData.password ||
      !registerData.role
    ) {
      toast.error('Please enter valid data.')
      return
    }

    // Perform data submission logic here
    // ...

    setRegisterData({
      name: '',
      email: '',
      username: '',
      password: '',
      role: '',
    })

    //console.log(registerData)

    ajax('/api/users/register', 'POST', null, registerData).then((userData) => {
      // console.log(userData)
      // console.log('user registered Succesfully')
      if (userData) {
        toast.info('user registered Succesfully,Please Proceed to Login')
      } else {
        toast.error('Bad Credentials')
      }
    })
  }

  return (
    <>
      <NavBar />
      <div className='container my-5 mx-auto border-4 border-dark p-2'>
        <div className='col-sm-6 offset-sm-3 border border-dark shadow p-3 rounded-2 register-bg'>
          <ToastContainer />
          <h3 className='m-3 text-primary'>Registration Page</h3>
          <form>
            <div className='mb-2 mt-5'>
              <label
                htmlFor='name'
                className='form-label font-weight-bold text-left'>
                Name
              </label>
              <input
                type='text'
                className='form-control'
                id='name'
                placeholder='Enter Name'
                name='name'
                value={registerData.name}
                onChange={changeHandle}
              />
            </div>

            <div className='mb-2 mt-2'>
              <label
                htmlFor='email'
                className='form-label font-weight-bold text-left'>
                Email
              </label>
              <input
                type='email'
                className='form-control'
                id='email'
                placeholder='Enter Email'
                name='email'
                value={registerData.email}
                onChange={changeHandle}
              />
            </div>

            <div className='mb-2 mt-2'>
              <label
                htmlFor='username'
                className='form-label font-weight-bold text-left'>
                Username
              </label>
              <input
                type='username'
                className='form-control'
                id='username'
                placeholder='Enter Username'
                name='username'
                value={registerData.username}
                onChange={changeHandle}
              />
            </div>

            <div className='mb-2 mt-2'>
              <label
                htmlFor='password'
                className='form-label font-weight-bold text-left'>
                Password
              </label>
              <input
                type='password'
                className='form-control'
                id='password'
                placeholder='Enter Password'
                name='password'
                value={registerData.password}
                onChange={changeHandle}
              />
            </div>

            <div className='mb-2 mt-2'>
              <label
                htmlFor='role'
                className='form-label font-weight-bold text-left'>
                Role
              </label>
              <select
                className='form-select'
                id='role'
                name='role'
                value={registerData.role}
                onChange={changeHandle}>
                <option value=''>Select a role</option>
                {roles.map((role) => (
                  <option key={role} value={role}>
                    {role}
                  </option>
                ))}
              </select>
            </div>

            <button
              type='button'
              className='btn btn-primary bg-primary p-2 px-5 mt-2'
              onClick={addData}>
              Register
            </button>
            <Link
              to='/'
              className='btn btn-secondary p-2 px-5 custom-margin-left mt-2'>
              Back
            </Link>
          </form>
        </div>
      </div>
    </>
  )
}

export default Register
