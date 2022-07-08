import { useRouter } from "next/router";
import { SubmitHandler, useForm } from "react-hook-form";
import productRepository from "../../repository/productRepository";
import { API_ENDPOINT } from "../../utils/api/endpoint";

export default function AddProduct() {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<InputProduct>();
  const router = useRouter()
  const onSubmit: SubmitHandler<InputProduct> = (data) => {
    productRepository.create("product/"+API_ENDPOINT.new,data).then(res=>{
      console.log(res);     
      router.replace("/product") 
    })
  };

  console.log(watch("name")); // watch input value by passing the name of it
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
                Product Name
              </label>
              <input                
                {...register("name")}
                type="text"
                id="email"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                
              />
            </div>
            <div className="mb-6">
              <label
                htmlFor="password"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >
                Price
              </label>
              <input
                type="text"
                id="password"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="฿"
                {...register("price", { required: true })}
              />
              {errors.price && <span>This field is required</span>}
            </div>
            <div className="mb-6">
              <label
                htmlFor="password"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >
                Balance
              </label>
              <input
                type="text"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="pic"
                {...register("balance", { required: true })}
              />
              {errors.balance && <span>This field is required</span>}
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
