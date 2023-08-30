import React, { useState } from 'react'
import { useLocalState } from '../util/useLocalStorage'
import ajax from '../Services/fetchService'
import { Button, Col, Container, Row, Form } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'

const Login = () => {
  const navigate = useNavigate()
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const [jwt, setJwt] = useLocalState('', 'jwt')

  function sendLoginRequest() {
    const reqBody = {
      username: username,
      password: password,
    }
    ajax('api/auth/login', 'POST', jwt, reqBody)
    fetch('api/auth/login', {
      headers: {
        'Content-Type': 'application/json',
      },
      method: 'post',
      body: JSON.stringify(reqBody),
    })
      .then((response) => {
        if (response.status === 200)
          return Promise.all([response.json(), response.headers])
        else return Promise.reject('Invalid Request')
      })
      .then(([body, headers]) => {
        setJwt(headers.get('authorization'))
        navigate('/dashboard')
      })
      .catch((message) => {
        alert(message)
      })
  }

  return (
    <>
      <Container className='mt-5 '>
        <Row className='justify-content-center'>
          <Col md='8' lg='6'>
            <Form.Group className='mb-5' controlId='username'>
              <Form.Label className='fs-4'>Username</Form.Label>
              <Form.Control
                type='email'
                id='username'
                size='lg'
                placeholder='email@gmail.com'
                value={username}
                onChange={(e) => {
                  setUsername(e.target.value)
                }}
              />
            </Form.Group>
          </Col>
        </Row>

        <Row className='justify-content-center'>
          <Col md='8' lg='6'>
            <Form.Group className='mb-3' controlId='password'>
              <Form.Label className='fs-4'>Password</Form.Label>
              <Form.Control
                type='password'
                id='username'
                value={password}
                size='lg'
                placeholder='password'
                onChange={(e) => {
                  setPassword(e.target.value)
                }}
              />
            </Form.Group>
          </Col>
        </Row>

        <Row className='justify-content-center'>
          <Col
            md='6'
            className='mt-2 d-flex flex-column gap-2 flex-md-row justify-content-md-between'>
            <Button
              id='submit'
              type='button'
              onClick={() => sendLoginRequest()}>
              Login
            </Button>
            <Button
              variant='secondary'
              type='button'
              onClick={() => navigate('/')}>
              Exit
            </Button>
          </Col>
        </Row>
      </Container>
    </>
  )
}

export default Login
