import * as React from 'react';

import {connect} from 'react-redux';

import App from '../../Components/Organisms/App';
import { IApplicationState } from '../../Redux/Store';


import { selectors } from '../../Redux/Store';
import Header from '../Header';
import Sidebar from '../Sidebar';

const mapStateToProps = (state: IApplicationState) => ({
    namespaceProperties: selectors.Ui.ConfigDoc.makeFilteredPropertiesByNamespace()(state),
    selectedNamespace: selectors.Ui.ConfigDoc.selectedNamespace(state),
    selectedProperty:  selectors.Ui.ConfigDoc.selectedProperty(state),
});

type AppContainerProps = ReturnType<typeof mapStateToProps>

class AppContainer extends React.PureComponent<AppContainerProps> {

    public render() {
        return (
            <AppWithMappedComponents {... this.props} />
        );
    }

}

// tslint:disable-next-line:max-classes-per-file
class AppWithMappedComponents extends App {

    protected renderSidebar(): JSX.Element {
        return (<Sidebar />);
    }

    protected renderHeader(): JSX.Element {
        return (<Header />);
    }

}

export default connect(mapStateToProps)(AppContainer);
