import React, {PureComponent} from 'react';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import './SidebarNamespaceList.scss';

class SidebarNamespaceList extends PureComponent {

    static propTypes = {
        namespaces: PropTypes.arrayOf(PropTypes.string).isRequired,
        onClickItem: PropTypes.func.isRequired,
    };

    render() {
        const {namespaces, onClickItem} = this.props;
        return (
            <div className="namespace-list">
                <ul>
                    {namespaces.map(namespaceName =>
                        <li key={namespaceName} onClick={() => onClickItem(namespaceName)}>
                            <FontAwesomeIcon icon="cube"/>
                            {namespaceName}
                        </li>
                    )}
                </ul>
            </div>
        );
    }

}

export default SidebarNamespaceList;
