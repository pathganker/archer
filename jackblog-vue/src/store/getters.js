const getters = {
  accessToken: state => state.auth.accessToken,
  isRefreshToken: state => state.auth.isRefreshToken,
  captchaKey: state => state.globalVal.captchaKey,
  backendArticle: state => state.backendArticle.draft,
  isSigin: state => state.auth.isSigin
}
export default getters
