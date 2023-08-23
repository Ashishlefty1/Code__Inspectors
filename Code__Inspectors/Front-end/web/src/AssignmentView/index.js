import React, { useState, useEffect } from 'react'
import { useLocalState } from '../util/useLocalStorage'
import ajax from '../Services/fetchService'
import {
  Badge,
  Button,
  ButtonGroup,
  Container,
  Dropdown,
  DropdownButton,
} from 'react-bootstrap'
import Col from 'react-bootstrap/Col'
import Form from 'react-bootstrap/Form'
import Row from 'react-bootstrap/Row'

const AssignmentView = () => {
  const assignmentId = window.location.href.split('/assignments/')[1]
  const [jwt, setJwt] = useLocalState('', 'jwt')
  const [assignment, setAssignment] = useState({ branch: '', githubUrl: '' })

  function updateAssignment(prop, value) {
    const newAssignment = { ...assignment }
    newAssignment[prop] = value
    setAssignment(newAssignment)

    console.log(assignment)
  }

  function save() {
    ajax(`/api/assignments/${assignmentId}`, 'PUT', jwt, assignment)
      // fetch(`/api/assignments/${assignmentId}`, {
      //   headers: {
      //     'Content-type': 'application/json',
      //     Authorization: `Bearer ${jwt}`,
      //   },
      //   method: 'PUT',
      //   body: JSON.stringify(assignment),
      // })
      //   .then((response) => {
      //     if (response.status === 200) return response.json()
      //   })
      .then((assignmentData) => {
        setAssignment(assignmentData)
      })
  }

  useEffect(() => {
    ajax(`/api/assignments/${assignmentId}`, 'GET', jwt)
      // fetch(`/api/assignments/${assignmentId}`, {
      //   headers: {
      //     'Content-type': 'application/json',
      //     Authorization: `Bearer ${jwt}`,
      //   },
      //   method: 'GET',
      // })
      //   .then((response) => {
      //     if (response.status === 200) return response.json()
      //   })
      .then((assignmentsData) => {
        if (assignmentsData.branch === null) assignmentsData.branch = ''
        if (assignmentsData.githubUrl === null) assignmentsData.githubUrl = ''

        setAssignment(assignmentsData)
        console.log(assignmentsData)
      })
  }, [])

  return (
    <Container className='mt-5'>
      <Row className='d-flex align-items-center'>
        <Col>
          {' '}
          <h1>Assignment {assignmentId} </h1>
        </Col>
        <Col>
          <Badge pill bg='info' style={{ fontSize: '1em' }}>
            {assignment.status}
          </Badge>
        </Col>
      </Row>

      {assignment ? (
        <>
          <Form.Group as={Row} className='my-3' controlId='formPlaintextEmail'>
            <Form.Label column sm='3' md='2'>
              Assignment Number:
            </Form.Label>
            <Col sm='9' md='8' lg='6'>
              <DropdownButton
                as={ButtonGroup}
                id='assignmentName'
                variant={'info'}
                title='Assignment 1'>
                {['1', '2', '3', '4', '5', '6', '7'].map((assignmentNum) => (
                  <Dropdown.Item eventKey={assignmentNum}>
                    {assignmentNum}
                  </Dropdown.Item>
                ))}
              </DropdownButton>
            </Col>
          </Form.Group>

          <Form.Group as={Row} className='my-3' controlId='formPlaintextEmail'>
            <Form.Label column sm='3' md='2'>
              GitHub URL
            </Form.Label>
            <Col sm='9' md='8' lg='6'>
              <Form.Control
                id='githubUrl'
                onChange={(e) => updateAssignment('githubUrl', e.target.value)}
                type='url'
                placeholder='http://github.com/username/repo_name'
                value={assignment.githubUrl}
              />
            </Col>
          </Form.Group>

          <Form.Group as={Row} className='mb-3' controlId='formPlaintextEmail'>
            <Form.Label column sm='3' md='2'>
              Branch
            </Form.Label>
            <Col sm='9' md='8'>
              <Form.Control
                id='branch'
                onChange={(e) => updateAssignment('branch', e.target.value)}
                value={assignment.branch}
                type='text'
                placeholder='for Example : main,master'
              />
            </Col>
          </Form.Group>

          <Button onClick={() => save()}>Submit Assignment</Button>
        </>
      ) : (
        <></>
      )}
    </Container>
  )
}

export default AssignmentView
