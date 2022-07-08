import {
  faArrowRightToBracket,
  faGear,
  faGears,
  faRightToBracket,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Menu, Transition } from "@headlessui/react";
import { Fragment} from "react";
import nookies from "nookies";
import { AUTH_CRED } from "../utils/constant";
import { useRouter } from "next/router";

export default function MenuSetting() {
  const router = useRouter();
  const logout = () => {
    nookies.destroy(null, AUTH_CRED);
    router.replace("/login");
  };

  return (
    <div>
      <Menu as="div" className="relative inline-block text-left">
        <div>
          <Menu.Button className="inline-flex items-center relative px-2 border rounded-full hover:shadow-lg">
            <div className="pl-1">
              <svg
                viewBox="0 0 32 32"
                xmlns="http://www.w3.org/2000/svg"
                aria-hidden="true"
                role="presentation"
                focusable="false"
                style={{
                  display: "block",
                  fill: "none",
                  height: 16,
                  width: 16,
                  stroke: "currentcolor",
                  strokeWidth: 3,
                  overflow: "visible",
                }}
              >
                <g fill="none" fillRule="nonzero">
                  <path d="m2 16h28" />
                  <path d="m2 24h28" />
                  <path d="m2 8h28" />
                </g>
              </svg>
            </div>
            <div className="block flex-grow-0 flex-shrink-0 h-10 w-12 pl-5">
              <svg
                viewBox="0 0 32 32"
                xmlns="http://www.w3.org/2000/svg"
                aria-hidden="true"
                role="presentation"
                focusable="false"
                style={{
                  display: "block",
                  height: "100%",
                  width: "100%",
                  fill: "currentcolor",
                }}
              >
                <path d="m16 .7c-8.437 0-15.3 6.863-15.3 15.3s6.863 15.3 15.3 15.3 15.3-6.863 15.3-15.3-6.863-15.3-15.3-15.3zm0 28c-4.021 0-7.605-1.884-9.933-4.81a12.425 12.425 0 0 1 6.451-4.4 6.507 6.507 0 0 1 -3.018-5.49c0-3.584 2.916-6.5 6.5-6.5s6.5 2.916 6.5 6.5a6.513 6.513 0 0 1 -3.019 5.491 12.42 12.42 0 0 1 6.452 4.4c-2.328 2.925-5.912 4.809-9.933 4.809z" />
              </svg>
            </div>
          </Menu.Button>
        </div>
        <Transition
          as={Fragment}
          enter="transition ease-out duration-100"
          enterFrom="transform opacity-0 scale-95"
          enterTo="transform opacity-100 scale-100"
          leave="transition ease-in duration-75"
          leaveFrom="transform opacity-100 scale-100"
          leaveTo="transform opacity-0 scale-95"
        >
          <Menu.Items className="absolute right-0 mt-2 w-56 origin-top-right divide-y z-10   divide-gray-100 rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
            <div className="px-1 py-1 ">
              <Menu.Item>
                {({ active }) => (
                  <button
                    className={`${
                      active ? "bg-stone-200" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm gap-2`}
                  >
                    {active ? (
                      <FontAwesomeIcon icon={faGears} />
                    ) : (
                      <FontAwesomeIcon icon={faGear} />
                    )}
                    Setting
                  </button>
                )}
              </Menu.Item>
              <Menu.Item>
                {({ active }) => (
                  <button
                    onClick={logout}
                    className={`${
                      active ? "bg-stone-200" : "text-gray-900"
                    } group flex w-full items-center rounded-md px-2 py-2 text-sm gap-2`}
                  >
                    {active ? (
                      <FontAwesomeIcon icon={faRightToBracket} />
                    ) : (
                      <FontAwesomeIcon icon={faArrowRightToBracket} />
                    )}
                    Logout
                  </button>
                )}
              </Menu.Item>
            </div>
          </Menu.Items>
        </Transition>
      </Menu>
    </div>
  );
}
