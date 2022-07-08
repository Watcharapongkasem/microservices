import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { RootState } from "../redux/store";
import user from "../repository/user";
import { API_ENDPOINT } from "../utils/api/endpoint";
import { setAuthCredentials } from "../utils/auth-utits";

export default function Login() {
  // const cart = useAppSelector((state: RootState) => state.cart);
  // const [userId, setUserId] = useState(1);
  // const dispatch = useAppDispatch();
  // useEffect(() => {
  //   dispatch(fetchOrderByUserId(userId));
  // }, []);
  const router = useRouter();
  const { register, handleSubmit } = useForm<InputRegister>();
  const onSubmit: SubmitHandler<InputRegister> = (data) => {
    user.login("auth/" + API_ENDPOINT.login, data).then((res) => {
      console.log(res);

      setAuthCredentials(res.data.token, res.data.userId, ["PASS"]);
      router.push("/product");
    });
  };

  return (
    <div className="">
      <div className="flex flex-col ">
        {/* Auth Card Container */}
        <div className="grid place-items-center mx-2 my-20 sm:my-auto">
          {/* Auth Card */}
          <div
            className="w-11/12 p-12 sm:w-8/12 md:w-6/12 lg:w-5/12 2xl:w-4/12 
            px-6 py-10 sm:px-10 sm:py-6 
            bg-white rounded-lg shadow-md lg:shadow-lg"
          >
            {/* Card Title */}
            <h2 className="text-center font-semibold text-3xl lg:text-4xl text-gray-800">
              Login
            </h2>
            <form className="mt-10" onSubmit={handleSubmit(onSubmit)}>
              {/* Email Input */}
              <label
                htmlFor="email"
                className="block text-xs font-semibold text-gray-600 uppercase"
              >
                E-mail
              </label>
              <input
                {...register("userName")}
                type="text"
                className="block w-full py-3 px-1 mt-2 
              text-gray-800 appearance-none 
              border-b-2 border-gray-100
              focus:text-gray-500 focus:outline-none focus:border-gray-200"
              />
              {/* Password Input */}
              <label
                htmlFor="password"
                className="block mt-2 text-xs font-semibold text-gray-600 uppercase"
              >
                Password
              </label>
              <input
                {...register("password")}
                id="password"
                type="password"
                name="password"
                placeholder="password"
                autoComplete="current-password"
                className="block w-full py-3 px-1 mt-2 mb-4
              text-gray-800 appearance-none 
              border-b-2 border-gray-100
              focus:text-gray-500 focus:outline-none focus:border-gray-200"
              />
              {/* Auth Buttton */}
              <button
                type="submit"
                className="w-full py-3 mt-10 bg-gray-800 rounded-sm
              font-medium text-white uppercase
              focus:outline-none hover:bg-gray-700 hover:shadow-none"
              >
                Login
              </button>
              {/* Another Auth Routes */}
              <div className="sm:flex sm:flex-wrap mt-8 sm:mb-4 text-sm text-center">
                <a href="register" className="flex-2 underline">
                  Create an Account
                </a>
              </div>
            </form>
          </div>
        </div>
      </div>

      {/* <div>Login</div>
      <div>Form Login</div>
      <div>
        {cart.orders.map((x, index) => {
          return (
            <div key={index}>
              {x.title} , {x.userId}
            </div>
          );
        })}
      </div>
      <button
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        onClick={() => {
          setUserId(userId + 1);
          dispatch(fetchOrderByUserId(userId));
        }}
      >
        Button
      </button> */}
    </div>
  );
}
