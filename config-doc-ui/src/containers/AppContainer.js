import React, {PureComponent} from 'react';
import { connect } from 'react-redux'
import UiActions from '../state/Ui/UiActions'
import DataSelectors from '../state/Data/DataSelectors'
import PropTypes from 'prop-types';
import App from '../components/templates/App/App'


const mapStateToProps = (state) => {
    return {
        namespaceNames: DataSelectors.allNamespaceNamesSelector(state)
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onSelectNamespace: (namespaceName) => {
            dispatch(UiActions.actions.selectNamespace(namespaceName))
        }
    }
}

class AppContainer extends PureComponent {
    static propTypes = {
        namespaceNames: PropTypes.arrayOf(PropTypes.string).isRequired,
        onSelectNamespace: PropTypes.func.isRequired,
    };

    render() {
        const {namespaceNames, onSelectNamespace} = this.props;
        return (
            <App sidebar={{namespaceNames, onSelectNamespace}} />
        );
    }
}


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(AppContainer)

