import { type Category } from "./Category"

export type Product = {
  id?: number //? näitab, et vabatahtlik, võib tühi olla
  name: string
  description: string
  price: number
  quantity: number
  category: Category
}