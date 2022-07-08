import Base from "./base";

class orderRepository extends Base<Order, Order> {

  checkout = async (url: string, variables: Order) => {
    return this.http<Order>(url, "post", variables);
  };
}

export default new orderRepository();
