import * as types from './mutation-types'

export const login = ({commit},option) => {
    return commit(types.LOGIN_SUCCESS,option)
}

