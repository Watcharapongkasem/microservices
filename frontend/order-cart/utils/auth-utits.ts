import { AUTH_CRED, TOKEN, PERMISSIONS } from "./constant";
import nookies from "nookies";

export function setAuthCredentials(
  token: string,
  userId: string,
  permissions: any
) {
  nookies.destroy(null, AUTH_CRED);
  nookies.set(null, AUTH_CRED, JSON.stringify({ token, userId, permissions }), {
    maxAge: 30 * 24 * 60 * 60,
  });
}

// export async function getServerSideProps(ctx:any) {
//     // Parse
//     const cookies = nookies.get(ctx)

//     // Set
//     nookies.set(ctx, 'fromGetInitialProps', 'value', {
//       maxAge: 30 * 24 * 60 * 60,
//       path: '/',
//     })

//     // Destroy
//     // nookies.destroy(ctx, 'cookieName')

//     return { cookies }
//   }

export function getAuthCredentials(context?: any): {
  token: string | null;
  userId: string | null;
  permissions: string[] | null;
} {
  let authCred;
  if (context) {
    authCred = nookies.get(context)[AUTH_CRED];
  } else {
    authCred = nookies.get()[AUTH_CRED];
  }
  if (authCred) {
    return JSON.parse(authCred);
  }
  return { token: null, userId: null, permissions: null };
}

export function isAuthenticated(_cookies: any): boolean {
  return (
    !!_cookies[TOKEN] &&
    Array.isArray(_cookies[PERMISSIONS]) &&
    !!_cookies[PERMISSIONS].length
  );
}
