interface Order {
  orderId: string;
  productId: string;
  name: string;
  value: number;
  userId: string;
  status: boolean;
}

interface OrderState {
  orders: Order[];
}

type OrderAction = {
  type: string;
  payload: any;
};

interface CheckOut {
  checkoutId: string;
  orderId: string;
}

type CheckOutAction = {
  type: string;
  payload: CheckOut;
};

type InputProduct = {
  name: string;
  balance: number;
  price: number;
};

type InputRegister = {
  userName: string;
  password: number;
};

type UpdateUser = {
  username: string;
};

type UpdateProduct = {
  productId: string;
  name: string;
  price: number;
  balance: number;
  value: number;
};

type getProduct = {
  productId: string;
  name: string;
  price: number;
  balance: number;
};

type productInti = {
  product: getProduct[];
};
