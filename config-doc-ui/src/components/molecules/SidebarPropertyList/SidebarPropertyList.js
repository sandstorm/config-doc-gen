import React, {PureComponent} from 'react';
import PropTypes from 'prop-types';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import './SidebarPropertyList.scss';

class SidebarPropertyList extends PureComponent {

    static propTypes = {
        properties: PropTypes.arrayOf(PropTypes.shape({
            name: PropTypes.string.isRequired,
            qualifiedName: PropTypes.string.isRequired,
            namespace: PropTypes.string.isRequired,
            accessibility: PropTypes.string.isRequired,
        })).isRequired,
        showQualifiedName: PropTypes.bool.isRequired,
        onClickItem: PropTypes.func.isRequired,
    };

    render() {
        const {properties, showQualifiedName, onClickItem} = this.props;
        return (
            <div className="property-list">
                <ul>
                    {properties.map(property =>
                        <li key={property.qualifiedName} onClick={() => onClickItem(property.qualifiedName)}>
                            <FontAwesomeIcon icon={this.iconForAccessibility(property.accessibility)}/>
                            {showQualifiedName ? property.qualifiedName : property.name}
                        </li>
                    )}
                </ul>
            </div>
        );
    }

    iconForAccessibility(accessibility) {
        switch (accessibility) {
            case 'API':
                return 'clipboard-check';
            case 'IMPLEMENTATION':
                return 'exclamation-circle';
            default:
                return 'question';
        }
    }

}

export default SidebarPropertyList;
