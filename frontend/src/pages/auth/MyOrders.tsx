
import useLoadItems from "../../hooks/useLoadItems";
import { type Order } from "../../models/Order";

function MyOrders() {

  //const [orders, setOrders] = useState([]);
  const {items: orders, loading}= useLoadItems<Order>("/my-orders", true);
  
  if (loading) {
    return <div>Loading....</div>
  }

  if (orders.length === 0) {
    return (<div>Ühtegi tellimust veel pole</div>)
  }
  return (
    <div className="orders">
      {orders.map(order => 
        <div key = {order.id} className="order">
          <div>Order nr: {order.id}</div>
          <div>{order.created.toString() }  </div>
          <div>{order.total} EUR </div>
          <div>{order.orderRows.map(row => 
          <span key={row.id} className="span">
            <span> {row.product.name}</span>
            <span> {row.quantity} tk |</span>
          </span>
            
          )}
          </div>
        </div>
      )}
    </div>
  )
}

export default MyOrders