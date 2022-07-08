import { faGears, faPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useRouter } from "next/router";
import Order from "../../components/order";

export default function Product() {
  const router = useRouter();
  const addProduct = () => {
    router.push("product/addProduct")
  }
  return (
    <>
      <div className="w-full flex justify-end">
        <div className="inline-flex items-center relative px-2 border rounded-full hover:shadow-lg my-3" onClick={addProduct}>
          <div className="flex h-10 w-12 items-center justify-center">
            <FontAwesomeIcon icon={faPlus} />
          </div>
        </div>
      </div>
      <Order></Order>
    </>
  );
}
