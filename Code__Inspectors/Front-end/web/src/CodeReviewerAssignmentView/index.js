import React, { useState, useEffect, useRef } from 'react'
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

const CodeReviewerAssignmentView = () => {
  const assignmentId = window.location.href.split('/assignments/')[1]
  const [jwt, setJwt] = useLocalState('', 'jwt')
  const [assignment, setAssignment] = useState({
    branch: '',
    githubUrl: '',
    number: null,
    status: null,
    codeReviewVideoUrl: null,
  })

  const [assignmentEnums, setAssignmentEnums] = useState([])
  const [assignmentStatuses, setAssignmentStatuses] = useState([])

  const prevAssignmentValue = useRef(assignment)

  function updateAssignment(prop, value) {
    const newAssignment = { ...assignment }
    newAssignment[prop] = value
    setAssignment(newAssignment)

    //console.log(assignment)
  }

  function save(status) {
    //this implies that the student is submitting the assignment for the first time
    if (status && assignment.status !== status) {
      updateAssignment('status', status)
    } else {
      persist()
    }
  }

  function persist() {
    ajax(`/api/assignments/${assignmentId}`, 'PUT', jwt, assignment).then(
      (assignmentData) => {
        setAssignment(assignmentData)
      }
    )
  }
  useEffect(() => {
    if (prevAssignmentValue.current.status !== assignment.status) {
      persist()
    }
    prevAssignmentValue.current = assignment
  }, [assignment])

  useEffect(() => {
    ajax(`/api/assignments/${assignmentId}`, 'GET', jwt).then(
      (assignmentResponse) => {
        let assignmentsData = assignmentResponse.assignment
        if (assignmentsData.branch === null) assignmentsData.branch = ''
        if (assignmentsData.githubUrl === null) assignmentsData.githubUrl = ''

        setAssignment(assignmentsData)
        setAssignmentEnums(assignmentResponse.assignmentEnums)
        setAssignmentStatuses(assignmentResponse.statusEnums)
        //console.log(assignmentsData)
        //console.log(assignmentResponse)
      }
    )
  }, [])

  return (
    <Container className='mt-5'>
      <Row className='d-flex align-items-center'>
        <Col>
          {assignment.number ? <h1>Assignment {assignment.number} </h1> : <></>}
        </Col>
        <Col>
          <Badge pill bg='info' style={{ fontSize: '1em' }}>
            {assignment.status}
          </Badge>
        </Col>
      </Row>

      {assignment ? (
        <>
          <Form.Group as={Row} className='my-3' controlId='githubUrl'>
            <Form.Label column sm='3' md='2'>
              GitHub URL
            </Form.Label>
            <Col sm='9' md='8' lg='6'>
              <Form.Control
                onChange={(e) => updateAssignment('githubUrl', e.target.value)}
                type='url'
                readOnly
                placeholder='http://github.com/username/repo_name'
                value={assignment.githubUrl}
              />
            </Col>
          </Form.Group>

          <Form.Group as={Row} className='mb-3' controlId='branch'>
            <Form.Label column sm='3' md='2'>
              Branch
            </Form.Label>
            <Col sm='9' md='8' lg='6'>
              <Form.Control
                onChange={(e) => updateAssignment('branch', e.target.value)}
                value={assignment.branch}
                type='text'
                readOnly
                placeholder='for Example : main,master'
              />
            </Col>
          </Form.Group>

          <Form.Group as={Row} className='my-3' controlId='codeReviewUrl'>
            <Form.Label column sm='3' md='2'>
              Video Review URL
            </Form.Label>
            <Col sm='9' md='8' lg='6'>
              <Form.Control
                onChange={(e) =>
                  updateAssignment('codeReviewUrl', e.target.value)
                }
                type='url'
                placeholder='http://screencast.com/yourvideo'
                value={assignment.codeReviewUrl}
              />
            </Col>
          </Form.Group>

          <div className='d-flex gap-5'>
            {assignment.status === 'Completed' ? (
              <Button
                size='lg'
                variant='secondary'
                onClick={() => save(assignmentStatuses[2].status)}>
                Re-Claim
              </Button>
            ) : (
              <Button
                size='lg'
                onClick={() => save(assignmentStatuses[4].status)}>
                Complete Review
              </Button>
            )}

            {assignment.status === 'Needs Update' ? (
              <Button
                size='lg'
                variant='secondary'
                onClick={() => save(assignmentStatuses[2].status)}>
                Re-Claim
              </Button>
            ) : (
              <Button
                size='lg'
                variant='danger'
                onClick={() => save(assignmentStatuses[3].status)}>
                Reject Assignment
              </Button>
            )}

            <Button
              size='lg'
              variant='secondary'
              onClick={() => (window.location.href = '/dashboard')}>
              Back
            </Button>
          </div>
        </>
      ) : (
        <></>
      )}
    </Container>
  )
}

export default CodeReviewerAssignmentView

//  fetch(`/api/assignments/${assignmentId}`, {
//   headers: {
//     'Content-type': 'application/json',
//     Authorization: `Bearer ${jwt}`,
//   },
//   method: 'GET',
// })
//   .then((response) => {
//     if (response.status === 200) return response.json()
//   })

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
