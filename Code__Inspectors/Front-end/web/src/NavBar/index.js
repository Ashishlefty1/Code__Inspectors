import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useLocation } from 'react-router-dom'
import { Button, Image } from 'react-bootstrap'
import logo from '../Images/coders-campus-logo.png'
import { useUser } from '../UserProvider'
import jwt_decode from 'jwt-decode'

function NavBar() {
  const navigate = useNavigate()
  const { pathname } = useLocation()
  const user = useUser()
  const [authorities, setAuthorities] = useState(null)

  useEffect(() => {
    if (user && user.jwt) {
      const decodedJwt = jwt_decode(user.jwt)
      setAuthorities(decodedJwt.authorities)
    }
  }, [user, user.jwt])

  return (
    <div className='NavBar nav d-flex justify-content-around justify-content-lg-between align-items-center'>
      <div className='ms-md-5 logo-container'>
        <Link to='/'>
          <Image src={logo} alt='logo' className='logo' />
        </Link>
      </div>
      <div>
        {user && user.jwt ? (
          <span
            className='link'
            onClick={() => {
              fetch('/api/auth/logout').then((response) => {
                if (response.status === 200) {
                  user.setJwt(null)
                  navigate('/')
                }
              })
            }}>
            Logout
          </span>
        ) : pathname !== '/login' ? (
          <Button
            variant='primary'
            className='ms-5 me-md-5 mb-2 mb-md-0'
            onClick={() => {
              navigate('/login')
            }}>
            Login
          </Button>
        ) : (
          <></>
        )}

        {pathname !== '/register' ? (
          <Button
            variant='primary'
            className='ms-5 me-md-5 mb-2 mb-md-0'
            onClick={() => {
              navigate('/register')
            }}>
            Register
          </Button>
        ) : (
          <></>
        )}

        {user && user.jwt ? (
          <Button
            className='ms-5 ms-md-5 me-md-5'
            onClick={() => {
              navigate('/dashboard')
            }}>
            Dashboard
          </Button>
        ) : (
          <></>
        )}
      </div>
    </div>
  )
}

export default NavBar
