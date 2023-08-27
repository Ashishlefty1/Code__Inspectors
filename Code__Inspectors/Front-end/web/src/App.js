import './App.css'
import React, { useEffect, useState } from 'react'
import { Route, Routes } from 'react-router-dom'
import Dashboard from './Dashboard'
import { useLocalState } from './util/useLocalStorage'
import Homepage from './Homepage'
import Login from './Login'
import PrivateRoute from './PrivateRoute'
import AssignmentView from './AssignmentView'
import 'bootstrap/dist/css/bootstrap.min.css'
import CodeReviewerDashboard from './CodeReviewerDashBoard'
import jwt_decode from 'jwt-decode'

function App() {
  const [jwt, setJwt] = useLocalState('', 'jwt')
  const [roles, setRoles] = useState(getRoleFromJWT(jwt))

  function getRoleFromJWT() {
    if (jwt) {
      const decodedJwt = jwt_decode(jwt)
      console.log(decodedJwt)
      return decodedJwt.authorities
    } else {
      return []
    }
  }

  return (
    <Routes>
      <Route
        path='/dashboard'
        element={
          roles.find((role) => role === 'ROLE_CODE_REVIEWER') ===
          'ROLE_CODE_REVIEWER' ? (
            <PrivateRoute>
              <CodeReviewerDashboard />
            </PrivateRoute>
          ) : (
            <PrivateRoute>
              <Dashboard />
            </PrivateRoute>
          )
        }
      />
      <Route
        path='/assignments/:id'
        element={
          <PrivateRoute>
            <AssignmentView />
          </PrivateRoute>
        }
      />

      <Route path='/login' element={<Login />} />
      <Route path='/' element={<Homepage />} />
    </Routes>
  )
}

export default App
