import * as React from 'react';
import {PickDefaultProps} from '../../../../types/defaultProps';
import {findIconDefinition, IconDefinition} from '@fortawesome/fontawesome-svg-core'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import './_SidebarPropertyList.scss';
import {Property} from "../../../Domain/Property";
import {Accessibility} from "../../../Domain/Accessibility";

const apiPropertyIcon: IconDefinition = findIconDefinition({prefix: 'fas', iconName: 'clipboard-check'})
const implementationPropertyIcon: IconDefinition = findIconDefinition({prefix: 'fas', iconName: 'exclamation-circle'})
const unknownPropertyIcon: IconDefinition = findIconDefinition({prefix: 'fas', iconName: 'question'})
const noPropertiesIcon: IconDefinition = findIconDefinition({prefix: 'fas', iconName: 'radiation'})

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
                        <FontAwesomeIcon icon={SidebarPropertyList.iconForAccessibility(property.accessibility)}/>
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

    private static iconForAccessibility(accessibility: Accessibility): IconDefinition {
        switch (accessibility) {
            case Accessibility.API:
                return apiPropertyIcon;
            case Accessibility.IMPLEMENTATION:
                return implementationPropertyIcon;
            default:
                return unknownPropertyIcon;
        }
    }

}
