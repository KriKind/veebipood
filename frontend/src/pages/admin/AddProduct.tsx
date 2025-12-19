import type { Category } from "../../models/Category";
import type { Product } from "../../models/Product";
import { ToastContainer, toast } from 'react-toastify';
import useFetch from "../../hooks/useFetch";
import useLoadItems from "../../hooks/useLoadItems";
import { useState } from "react";

//rfce
function AddProduct() {
    const[product, setProduct] = useState<Product>({
        "name": "",
        "price": 0,
        "description": "",
        "quantity": 0,
        "category": {
            "id": 1,
            "name": ""
        }
    })

    const backendQuery = useFetch();
    const [categories, loading]: Category[] = useLoadItems("/categories", false);


//   function add() {

//   }

  const add = async() => {
    if (product.name === "") {
        toast.error("Cannot add without name")
        return
    }
    if (product.price <= 0) {
      toast.error("Price cannot be negative")
      return
    }
    
    backendQuery("/products", "POST", product, "added-product")
    // try {
    //   const res = await fetch("http://localhost:8080/products", {
    //     method: "POST",
    //     body: JSON.stringify(product),
    //     headers: {
    //       "Content-Type": "application/json"
    //     }
    //   })
    //   const json = await res.json()
    //   // console.log(json)
    //   if (json.message && json.status &&  json.timestamp) {
    //     console.log("ERROR")
    //     console.log(json)
    //     toast.error(json.message)
    //   } else {
    //     toast.success("toode lisatud")
    //   }
    // } catch(error) {
    //   console.log("CATCH")
    //   console.log(error)
    //   toast.error(String(error))
    // }
  }

  return (
    <div>
        <div>Ajutine väljakuvamine: {JSON.stringify(product)}</div>
        <label>Name</label> <br />
        <input onChange={(e) => setProduct({...product, "name": e.target.value})} type="text" /> <br />
        <label>Price</label> <br />
        <input onChange={(e) => setProduct({...product, "price": Number(e.target.value)})} type="number" /> <br />
        <label>Description</label> <br />
        <input onChange={(e) => setProduct({...product, "description": e.target.value})} type="text" /> <br />
        <label>Quantity</label> <br />
        <input onChange={(e) => setProduct({...product, "quantity": Number(e.target.value)})} type="number" /> <br />
        <label>Category</label> <br />
        {/* <input onChange={(e) => setProduct({...product, "category": e.target.value})} type="text" /> <br /> */}
        <select onChange={(e) => setProduct({...product, "category": {"id": Number(e.target.value), "name": ""}})}>
        {
          categories.map(category =>
              <option key={category.id} value={category.id}>{category.name}</option>
          )
        }
        </select> <br />
        <button onClick={add}>ADD</button>
        <ToastContainer />
    </div>
  )
}

export default AddProduct