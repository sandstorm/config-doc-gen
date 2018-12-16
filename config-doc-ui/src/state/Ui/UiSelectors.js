import {createSelector} from 'reselect';
import {DataSelectors} from '../Data/DataSelectors';

const sidebarNamespacesSelector = state => createSelector(
    DataSelectors.allNamespacesSelector,
    allNamespaces => allNamespaces
        .map(namespace => namespace.name)
        .sort()
)

const sidebarNamespacesSelector = state => createSelector(
    DataSelectors.allNamespacesSelector,
    allNamespaces => allNamespaces
        .map(namespace => namespace.name)
        .sort()
)

export default {
    sidebarNamespacesSelector,
    sidebarPropertiesSelector,
}
