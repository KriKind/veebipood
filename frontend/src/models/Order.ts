import { type OrderRow } from "./OrderRow"

export type Order = {
  id?: number //? näitab, et vabatahtlik, võib tühi olla
  created: Date,
  total: number,
  orderRows: OrderRow[]
}