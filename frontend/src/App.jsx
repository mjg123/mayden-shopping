import { useState } from 'react'

import { ShoppingList } from './components/ShoppingList.jsx'
import { ShoppingListAPI } from './api/ShoppingListAPI.js'
import { BrowserRouter, Routes, Route, useNavigate, Link } from 'react-router-dom'


function CreateNewShoppingList({ setShoppingList }) {
  const nav = useNavigate();

  return (<button id="create-new-list" className="input" onClick={() => {
    ShoppingListAPI.createNewShoppingList((newList) => {
      setShoppingList(newList);
      nav("/" + newList.id, { replace: false });
    })
  }}>
    📋 New Shopping List
  </button>)
}

function App() {
  const [shoppingList, setShoppingList] = useState(null)

  return (
    <>
      <BrowserRouter>

      <nav>
          <ul>
            <li>🏠 <Link to="/">Home</Link></li>
          </ul>
        </nav>

        <h1>Trolley Time</h1>

        <main>

          <Routes>
            <Route path="/" element={<CreateNewShoppingList setShoppingList={setShoppingList} />} />
            <Route path="/:id" element={<ShoppingList shoppingList={shoppingList} setShoppingList={setShoppingList} />} />
          </Routes>

        </main>

        <footer>
          🐝 Created by Matthew Gilliard in 2025 for Mayden 🐝
        </footer>
      </BrowserRouter >
    </>
  )
}

export default App
