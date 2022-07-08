import { configureStore } from '@reduxjs/toolkit'
import cart from './reducer/cart'
import checkout from './reducer/checkout'
import order from './reducer/cart'
import product from './reducer/product'


const store = configureStore({ reducer : {product,cart} })

export default store

// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<typeof store.getState>
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch