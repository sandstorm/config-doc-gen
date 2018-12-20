import * as React from 'react';
import { connect } from 'react-redux';

import { push } from 'connected-react-router'

import { selectors } from '../../Redux/Store';

import AppSidebarDefaultView from '../../Components/Organisms/AppSidebarDefaultView';
import { IApplicationState } from '../../Redux/Store';

import { UiItem } from 'src/Domain/Ui/UiItem';

const mapStateToProps = (state: IApplicationState) => ({
    namespaces: selectors.Ui.ConfigDoc.filteredNamespaces(state),
    properties: selectors.Ui.ConfigDoc.filteredProperties(state),
    selectedNamespace: selectors.Ui.ConfigDoc.selectedNamespaceUiItem(state),
    selectedProperty: selectors.Ui.ConfigDoc.selectedPropertyUiItem(state),
});

const mapDispatchToProps = {
    push
}

type SidebarDefaultViewProps = ReturnType<typeof mapStateToProps> & typeof mapDispatchToProps;

class SidebarDefaultView extends React.PureComponent<SidebarDefaultViewProps> {
    public render(): JSX.Element {
        const navToNamespace = (namespace: UiItem) => this.props.push('/namespace/' + namespace.identifier);
        const navToProperty = (property: UiItem) => this.props.push('/property/' + property.identifier);
        return (<AppSidebarDefaultView {...this.props} onSelectNamespace={navToNamespace} onSelectProperty={navToProperty} />);
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SidebarDefaultView);
