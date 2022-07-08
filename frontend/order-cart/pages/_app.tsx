import "../styles/globals.css";
import type { AppProps } from "next/app";
import { Provider } from "react-redux";
import store from "../redux/store";
import { useEffect, useState } from "react";
import Navbar from "../components/navbar";

function MyApp({ Component, pageProps }: AppProps) {
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
    }, 2000);
  }, []);
  return (
    <>
      <Provider store={store}>
        <Navbar></Navbar>
        <div className="p-3">
          <Component {...pageProps} />
        </div>
      </Provider>
    </>
  );
}

export default MyApp;
