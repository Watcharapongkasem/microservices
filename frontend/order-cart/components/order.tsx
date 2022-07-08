import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartShopping, faDollar } from "@fortawesome/free-solid-svg-icons";
import { useEffect } from "react";
import Image from 'next/image'
import { RootState } from "../redux/store";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { fetchProductAll } from "../redux/reducer/product";
import { newOrderByProductId } from "../redux/reducer/cart";
import { getAuthCredentials } from "../utils/auth-utits";

export default function Order() {
  const state = useAppSelector((state: RootState) => state.product);
  const { userId } = getAuthCredentials();
  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(fetchProductAll());
  }, []);
  const newOrder = (data: getProduct) => {
    dispatch(newOrderByProductId(Object.assign({}, data, { userId })));
  };

  return (
    <>
      <div className="grid grid-cols-3 gap-4">
        {state.product.map((data, index) => {
          return (
            <div key={index}>
              <div>
                <Image
                  className="object-cover max-h-80 w-full"
                  src="https://images.pexels.com/photos/1464625/pexels-photo-1464625.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                  alt=""
                />
              </div>
              <div className="flex flex-row text-center">
                <div className="basis-1/4 self-center">{data.name}</div>
                <div className="basis-2/4 self-center">
                  {data.price} <FontAwesomeIcon icon={faDollar} />
                </div>
                <button
                  onClick={() => newOrder(data)}
                  type="button"
                  className="basis-1/4 border border-gray-200 bg-gray-200 text-gray-700 rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-300 focus:outline-none focus:shadow-outline"
                >
                  <FontAwesomeIcon icon={faCartShopping} />
                </button>
              </div>
            </div>
          );
        })}
      </div>
    </>
  );
}
