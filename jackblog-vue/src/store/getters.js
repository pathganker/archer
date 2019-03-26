const getters = {
  accessToken: state => state.auth.token,
  isRefreshToken: state => state.auth.isRefreshToken,
  captchaKey: state => state.globalVal.captchaKey,
  backendArticle: state => state.backendArticle.draft,
  isSigin: state => state.auth.isSigin
}
export default getters
