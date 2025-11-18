import { useState } from "react"
import type { Product } from "../models/Product"

//rfce
function Cart() {
  const [products, setProducts] = useState<Product[]>(JSON.parse(localStorage.getItem("cart") || "[]"))
  
  function empty() {
    setProducts([]) // uuendab html-i siin lehel
    localStorage.setItem("cart", "[]")
    localStorage.setItem("cart", JSON.stringify([]))

  }
  
  return (
    <div>
      <button onClick={empty}>Empty</button>
      
      {products.map(product =>
        <div key={product.id}>
          <div>{product.name}</div>
          <div>{product.price}</div>
        </div>
      )}
    </div>
  )
}

export default Cart