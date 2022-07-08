import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrashCan } from "@fortawesome/free-solid-svg-icons";
import { getAuthCredentials } from "../../utils/auth-utits";
import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../../redux/hook";
import {
  checkoutOrderByOrderId,
  deleteOrderByOrderId,
  fetchAllOrder,
} from "../../redux/reducer/cart";
import { RootState } from "../../redux/store";

export default function CheckOut() {
  const { userId } = getAuthCredentials();
  const state = useAppSelector((state: RootState) => state.cart);
  const dispatch = useAppDispatch();
  useEffect(() => {
    if (userId) {
      dispatch(fetchAllOrder(userId));
    }
  }, []);
  const deleteCheckOut = (orderId: string) => {
    dispatch(deleteOrderByOrderId(orderId));
  };
  const checkoutOrder = (order:Order) => {
    dispatch(checkoutOrderByOrderId(order));
  };
  return (
    <>
      {state.orders.map((data, index) => {
        return (
          <div className="flex justify-center m-4 gap-5" key={index}>
            <div className="grid grid-rows-2 gap-4 w-4/6 grid-cols-2 border-2 border-b-4 border-gray-200 rounded-xl hover:bg-gray-50 p-4">
              <div className="row-span-2 w-4/6 justify-self-center">
                <img
                  className="object-cover max-h-48 w-full"
                  src="https://images.pexels.com/photos/1464625/pexels-photo-1464625.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                  alt=""
                />
              </div>
              <div className="row-span-2 grid grid-rows-3 grid-flow-col gap-4 w-full">
                <div>{data.name}</div>
                <div>{data.value ? data.value : 0} pic</div>
                <button
                  onClick={() => {
                    deleteCheckOut(data.orderId);
                  }}
                  type="button"
                  className="row-start-3 w-1/5 border border-gray-200 bg-gray-200 text-gray-700 rounded-md px-4 py-2 transition duration-500 ease select-none hover:bg-gray-300 focus:outline-none focus:shadow-outline"
                >
                  <FontAwesomeIcon icon={faTrashCan} />
                </button>
              </div>
            </div>
            <div className="flex justify-center">
              <div className="w-4/6 flex justify-end">
                <a
                  onClick={() => {
                    checkoutOrder(data);
                  }}
                  className="flex bg-gray-500 rounded-xl  text-white px-4 py-3 transition duration-300 ease-in-out hover:bg-gray-600 mr-3"
                >
                  <div className="checkout-button">check out</div> 
                </a>
              </div>
            </div>
          </div>
        );
      })}
    </>
  );
}
