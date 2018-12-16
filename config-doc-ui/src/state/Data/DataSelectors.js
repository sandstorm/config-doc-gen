import {createSelector} from 'reselect'

const allNamespacesSelector = state => state.data.configDoc.namespaces
const allPropertiesSelector = state => state.data.configDoc.properties

const makePropertiesForNamespaceSelector = (namespaceName) => createSelector(
    allPropertiesSelector,
    properties => properties.filter(property => property.namespace === namespaceName)
)

export default {
    allNamespacesSelector,
    allPropertiesSelector,
    makePropertiesForNamespaceSelector
}
