import { useContext, useState } from "react"
import type { OrderRow } from "../models/OrderRow"
import { CartSumContext } from "../context/CartSumContext"

//rfce

// renderdamine - esimest korda lehele tulek
// re-rendredamine - useState setteri käima panek, HTNL-s muudetakse olekuid

function Cart() {
  const [orderRows, setOrderRows] = 
  useState<OrderRow[]>(JSON.parse(localStorage.getItem("cart") || "[]"))
  const {setCartSum} = useContext(CartSumContext)
  
  function empty() {
    setOrderRows([]) // uuendab html-i siin lehel
    localStorage.setItem("cart", "[]")
    // localStorage.setItem("cart", JSON.stringify([]))
    // localStorage.setItem("cart",String([]))
    setCartSum(calculateCartSum())

  }
  
  function decreaseQuantity(index: number) {
    orderRows[index].quantity--
    if (orderRows[index].quantity <= 0) {
      deleteProduct(index)
    }
    setOrderRows(orderRows.slice())
    localStorage.setItem("cart", JSON.stringify(orderRows))
    setCartSum(calculateCartSum())
  }

  function increaseQuantity(index: number) {
    orderRows[index].quantity++
    setOrderRows(orderRows.slice())
    localStorage.setItem("cart", JSON.stringify(orderRows))
    setCartSum(calculateCartSum())
  }

  function deleteProduct(index: number) {
    orderRows.splice(index,1) // Javascriptis kasutamiseks kus esimene nr on mitmes, teine mitu tk
    setOrderRows(orderRows.slice())
    // setProducts([...products()])
    localStorage.setItem("cart", JSON.stringify(orderRows))
    setCartSum(calculateCartSum())
  }

  // localStorage.setItem("VÕTI", "väärtus stringi kujul") --> panen
  // localStorage.setItem("VÕTI") --> võtan

  // LocalStorage-sse array osas ühe juurde lisamiseks:
  // 1. võtan localStorage-st vanad väärtused (localStorage.getItem)
  // 1.b kui pole seal midagi olemas, siis võta tühi array ( || [])
  // 2. võtan jutumärgid maha (JSON.parse)
  // 3. lisan ühe / kustutan ühe (.push() või .splice())
  // 4. lisan jutumärgid tagasi array-le (JSON.stringify)
  // 5. lisan localStorage-sse tagasi (localStorage.setItem)


  function calculateCartSum() {
    let sum = 0
    orderRows.forEach(row => sum += row.product.price * row.quantity)
    return sum
  }

  function pay() {
    fetch("http://localhost:8080/order/1", {
      method: "POST",
      body: JSON.stringify(orderRows),
      headers: {
        "Content-type": "application/json"
      }
    })
    .then(res => res.json())
    .then(json => console.log(json))
  }

  return (
    <div>
      <button onClick={empty}>Empty</button>
      
      {orderRows.map((orderRow, index) =>
        <div key={index}>
          <div>{orderRow.product.name}</div>
          <div>{orderRow.product.price} EUR</div>
          <button onClick={() => decreaseQuantity(index)}>-</button>
          <div>{orderRow.quantity} tk</div>
          <button onClick={() => increaseQuantity(index)}>+</button>
          <div>{(orderRow.product.price * orderRow.quantity).toFixed(2)} EUR</div>
          <button onClick={() => deleteProduct(index)}>x</button>
        </div>
      )}
      <br /> <br />
      <div> Ostukorvi kogusumma: {calculateCartSum().toFixed(2)}</div>

      <button onClick={pay}>Maksa</button>
    </div>
  )
}

export default Cart