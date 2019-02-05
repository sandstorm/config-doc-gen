import * as React from 'react';
import {connect} from 'react-redux';

import { push } from 'connected-react-router'

import {actions, selectors} from '../../Redux/Store';

import AppHeader from "../../Components/Organisms/AppHeader/AppHeader";
import { IApplicationState } from '../../Redux/Store';

const mapStateToProps = (state: IApplicationState) => ({
    configDocModuleName: selectors.Data.ConfigDoc.moduleName(state),
    searchTerm: selectors.Ui.ConfigDoc.searchTerm(state),
    selectedNamespace: selectors.Ui.ConfigDoc.selectedNamespaceUiItem(state),
    selectedProperty: selectors.Ui.ConfigDoc.selectedPropertyUiItem(state),
});

const mapDispatchToProps = {
    onSearchTermChanged: actions.Ui.ConfigDoc.changeSearchTerm,
    push,
}

type HeaderProps = ReturnType<typeof mapStateToProps> & typeof mapDispatchToProps;

class Header extends React.PureComponent<HeaderProps> {
    public render(): JSX.Element {
        const appIconClickedHandler = () => {this.props.push('/')}
        return (<AppHeader {...this.props} onAppIconClicked={appIconClickedHandler}/>);
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Header);
