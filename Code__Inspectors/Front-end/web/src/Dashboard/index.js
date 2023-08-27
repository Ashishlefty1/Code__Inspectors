import React, { useEffect, useState } from 'react'
import { useLocalState } from '../util/useLocalStorage'
import Card from 'react-bootstrap/Card'
import ajax from '../Services/fetchService'
import { Badge, Button, Col, Row } from 'react-bootstrap'

const Dashboard = () => {
  const [jwt, setJwt] = useLocalState('', 'jwt')
  const [assignments, setAssignments] = useState(null)

  useEffect(() => {
    ajax('api/assignments', 'GET', jwt)
      // fetch('api/assignments', {
      //   headers: {
      //     'Content-Type': 'application/json',
      //     Authorization: `Bearer ${jwt}`,
      //   },
      //   method: 'GET',
      // })
      // .then((response) => {
      //   if (response.status === 200) return response.json()
      // })
      .then((assignmentsData) => {
        setAssignments(assignmentsData)
        //console.log(assignmentsData)
      })
  }, [])

  function createAssignment() {
    ajax('api/assignments', 'POST', jwt).then((assignment) => {
      window.location.href = `/assignments/${assignment.id}`
    })
  }

  return (
    <div style={{ margin: '2em' }}>
      <Row>
        <Col>
          <div
            className='d-flex justify-content-end'
            style={{ cursor: 'pointer' }}
            onClick={() => {
              setJwt(null)
              window.location.href = '/login'
            }}>
            Logout
          </div>
        </Col>
      </Row>
      <div className='mb-4'>
        <Button onClick={() => createAssignment()}>
          Submit New Assignment
        </Button>
      </div>
      {assignments ? (
        <div
          className='d-grid gap-5'
          style={{ gridTemplateColumns: 'repeat(auto-fit,18rem)' }}>
          {assignments.map((assignment) => (
            <Card
              key={assignment.id}
              style={{ width: '18rem', height: '18rem' }}>
              <Card.Body className='d-flex flex-column justify-content-around'>
                <Card.Title>Assignment#{assignment.number}</Card.Title>
                <div className='d-flex align-item-start'>
                  <Badge pill bg='info' style={{ fontSize: '1em' }}>
                    {assignment.status}
                  </Badge>
                </div>

                <Card.Text style={{ marginTop: '1em' }}>
                  <p>
                    <b>GitHub URL:</b>
                    {assignment.githubUrl}
                  </p>
                  <p>
                    <b>Branch:</b>
                    {assignment.branch}
                  </p>
                </Card.Text>

                <Button
                  variant='secondary'
                  onClick={() => {
                    window.location.href = `/assignments/${assignment.id}`
                  }}>
                  Edit
                </Button>
              </Card.Body>
            </Card>
          ))}
        </div>
      ) : (
        <></>
      )}
    </div>
  )
}

export default Dashboard
