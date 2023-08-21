import React from 'react'
import { useLocalState } from '../util/useLocalStorage'

const Dashboard = () => {
  const [jwt, setJwt] = useLocalState('', 'jwt')

  function createAssignment() {
    fetch('api/assignments', {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${jwt}`,
      },
      method: 'POST',
    })
      .then((response) => {
        if (response.status === 200) return response.json()
      })
      .then((data) => {
        console.log(data)
      })
  }

  return (
    <div>
      <button onClick={() => createAssignment()}>Submit New Assignment</button>
    </div>
  )
}

export default Dashboard
