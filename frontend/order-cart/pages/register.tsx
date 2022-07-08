import { useRouter } from "next/router";
import { SubmitHandler, useForm } from "react-hook-form";
import user from "../repository/user";
import { API_ENDPOINT } from "../utils/api/endpoint";
import { setAuthCredentials } from "../utils/auth-utits";

export default function Register() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<InputRegister>();

  const router = useRouter();
  const onSubmit: SubmitHandler<InputRegister> = (data) => {
    // console.log(data);

    user.register("auth/" + API_ENDPOINT.register, data).then((res) => {
      if (res) {
        router.push("login");
      }
    });
  };
  return (
    <>
      <div className="flex justify-center">
        <form onSubmit={handleSubmit(onSubmit)} className="w-4/6">
          {/* register your input into the hook by invoking the "register" function */}
          <div>
            <div className="mb-6">
              <label
                htmlFor="email"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >
                Username
              </label>
              <input
                defaultValue="test"
                {...register("userName")}
                type="text"
                id="email"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="john.doe@company.com"
              />
            </div>
            <div className="mb-6">
              <label
                htmlFor="password"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >
                Password
              </label>
              <input
                type="password"
                id="password"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="฿"
                {...register("password", { required: true })}
              />
              {errors.password && <span>This field is required</span>}
            </div>
          </div>
          <input
            type="submit"
            className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          />
        </form>
      </div>
    </>
  );
}
