import {
  faCartArrowDown,
  faGears,
  faSackDollar,
  faUserAlt,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link";
import { useEffect, useState } from "react";
import { getAuthCredentials, isAuthenticated } from "../utils/auth-utits";
import MenuSetting from "./menu-setting";

export default function Navbar() {

  const { token, userId, permissions } = getAuthCredentials();
  const [auth, setAuth] = useState(false);

  useEffect(() => {
    setAuth(isAuthenticated({ token, permissions }));
  }, [token]);

  const CheckAuth = () => {
    return auth ? (
      <>
        <MenuSetting></MenuSetting>
      </>
    ) : (
      <>
        <Link href="/login" replace>
          <a
            className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full"
            href="#"
          >
            <div className="flex items-center relative cursor-pointer whitespace-nowrap gap-2">
              <FontAwesomeIcon icon={faUserAlt} />
              Login
            </div>
          </a>
        </Link>
      </>
    );
  };

  return (
    <>
      <div className="flex p-3">
        <div className="flex mr-4 items-center flex-auto justify-center">
          <Link href="/product">
            <a
              className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full"
              href="#"
            >
              <div className="flex items-center relative cursor-pointer whitespace-nowrap gap-2">
                <FontAwesomeIcon icon={faSackDollar} />
                Product
              </div>
            </a>
          </Link>
        </div>
        <div className="flex mr-4 items-center flex-auto justify-center">
          <Link href={`/checkout/${userId}`}>
            <a
              className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full"
              href="#"
            >
              <div className="flex items-center relative cursor-pointer whitespace-nowrap gap-2">
                <FontAwesomeIcon icon={faCartArrowDown} />
                CheckOut
              </div>
            </a>
          </Link>
        </div>
        <div className="flex flex-auto justify-end">
          <CheckAuth></CheckAuth>
        </div>
      </div>
    </>
  );
}
