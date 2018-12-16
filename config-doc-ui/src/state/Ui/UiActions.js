const initialState = {
    foo: 'bar',
    selectedNamespace: null
}

const actionTypes = {
    SELECT_NAMESPACE: 'configdocgen.ui.SELECT_NAMESPACE'
}

const actions = {
    selectNamespace: (namespaceName) => {
        return {
            type: actionTypes.SELECT_NAMESPACE,
            payload: {namespaceName}
        }
    },
}

export default {
    reducer: (state = initialState, action) => {
        switch (action.type) {
            case actionTypes.SELECT_NAMESPACE: {
                return {
                    ...state,
                    selectedNamespace: action.payload.namespaceName
                }
            }
            default:
                return state
        }
    },
    actions,
}

