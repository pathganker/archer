const getters = {
  accessToken: state => state.auth.accessToken,
  isRefreshToken: state => state.auth.isRefreshToken,
  captchaKey: state => state.globalVal.captchaKey
}
export default getters
