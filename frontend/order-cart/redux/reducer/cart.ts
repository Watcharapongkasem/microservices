import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import orderRepository from "../../repository/orderRepository";
import { API_ENDPOINT } from "../../utils/api/endpoint";
import * as Types from "../constants/actionType";
const initialState: OrderState = {
  orders: [],
};

export const newOrderByProductId = createAsyncThunk(
  "order/UserId",
  async (data: any) => {
    const response = await orderRepository.create(
      "order/" + API_ENDPOINT.new,
      data
    );
    return (await response.data) as Order;
  }
);

export const fetchAllOrder = createAsyncThunk(
  "order/all",
  async (userId: string) => {
    const response = await orderRepository.all(
      "order/" + API_ENDPOINT.all + "?userId=" + userId
    );
    return (await response.data) as Order[];
  }
);

export const deleteOrderByOrderId = createAsyncThunk(
  "order/delete",
  async (orderId: string) => {
    const response = await orderRepository.delete(
      "order/" + "?orderId=" + orderId
    );
    if (await response.data) {
      return orderId;
    }
    return "";
  }
);

export const checkoutOrderByOrderId = createAsyncThunk(
  "order/checkout",
  async (data:Order) => {
    const response = await orderRepository.checkout(
      "checkout/" + API_ENDPOINT.new,data
    );
    return (await response.data) ? data.orderId :"";
  }
);

export const orderSlice = createSlice({
  name: "order",
  initialState,
  // The `reducers` field lets us define reducers and generate associated actions
  reducers: {
    // Use the PayloadAction type to declare the contents of `action.payload`
    incrementByAmount: (state, action: PayloadAction<Order>) => {
      state["orders"] = [...state["orders"], action.payload];
    },
  },
  extraReducers: (builder) => {
    builder.addCase(newOrderByProductId.fulfilled, (state, action) => {
      state["orders"] = [...state["orders"], action.payload];
    });
    builder.addCase(fetchAllOrder.fulfilled, (state, action) => {
      state["orders"] = action.payload;
    });
    builder.addCase(deleteOrderByOrderId.fulfilled, (state, action) => {
      state["orders"] = state["orders"].filter(
        (i) => i.orderId != action.payload
      );
    });
    builder.addCase(checkoutOrderByOrderId.fulfilled, (state, action) => {
      state["orders"] = state["orders"].filter(
        (i) => i.orderId != action.payload
      );
    });
  },
});

export const { incrementByAmount } = orderSlice.actions;

export default orderSlice.reducer;
