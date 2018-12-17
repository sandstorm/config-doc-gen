import * as React from 'react';
import {PickDefaultProps} from '../../../../types/defaultProps';
import './_SidebarPropertyList.scss';
import {Property} from "../../../Domain/Property";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {iconForAccessibility, noPropertiesIcon} from "../../../icons";

//
// Props
//
interface SidebarPropertyListProps {
    readonly properties: ReadonlyArray<Property>;
    //readonly showQualifiedName: boolean;
    //readonly onClickItem: (itemName: string) => void
}

type DefaultProps = PickDefaultProps<SidebarPropertyListProps, 'properties'>;

const defaultProps: DefaultProps = {
    properties: [],
};

//
// State
//
interface SidebarPropertyListState {
}

const initialTemplateState: SidebarPropertyListState = {};

//
// Class
//
export default class SidebarPropertyList extends React.PureComponent<SidebarPropertyListProps, SidebarPropertyListState> {
    public static readonly defaultProps = defaultProps;

    public constructor(props: SidebarPropertyListProps) {
        super(props);
        this.state = initialTemplateState;
    }

    public render(): JSX.Element {
        return (
            <div className="property-list">
                {
                    this.props.properties.length > 0 ?
                        this.renderList() : this.renderEmptyNotification()
                }

            </div>
        );
    }

    private renderList(): JSX.Element {
        return (
            <ul>
                {this.props.properties.map(property =>
                    <li key={property.qualifiedName} onClick={() => /*onClickItem(property.qualifiedName)*/ ""}>
                        <FontAwesomeIcon icon={iconForAccessibility(property.accessibility)}/>
                        {true ? property.qualifiedName : property.name}
                    </li>
                )}
            </ul>
        );
    }

    private renderEmptyNotification(): JSX.Element {
        return (
            <div className="property-list--empty">
                <FontAwesomeIcon icon={noPropertiesIcon}/>
                No Properties!
            </div>
        );
    }

}
