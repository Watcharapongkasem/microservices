import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import productRepository from "../../repository/productRepository";
import { API_ENDPOINT } from "../../utils/api/endpoint";

const initialState: productInti = {
  product: [],
};

export const fetchCheckOutAll = createAsyncThunk(
  "product" + API_ENDPOINT.all,
  async () => {
    const response = await productRepository.all("product/" + API_ENDPOINT.all);
    return (await response.data) as getProduct[];
  }
);

export const productSlice = createSlice({
  name: "checkOut",
  initialState,
  // The `reducers` field lets us define reducers and generate associated actions
  reducers: {
    // Use the PayloadAction type to declare the contents of `action.payload`
    // incrementByAmount: (state, action: PayloadAction<Order>) => {
    //   state["orders"] = [...state["orders"], action.payload];
    // },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchCheckOutAll.fulfilled, (state, action) => {
      state.product = action.payload;
    });
  },
});

// export const { incrementByAmount } = counterSlice.actions;

export default productSlice.reducer;