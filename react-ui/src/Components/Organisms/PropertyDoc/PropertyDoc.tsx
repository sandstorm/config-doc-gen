import * as React from 'react';

import { Accessibility } from '../../../Domain/Accessibility';
import Property from '../../../Domain/Property';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { iconForProperty } from "../../../icons";

// Props
//
interface IPropertyDocProps {
    readonly property: Property;

    // actions
}

type DefaultProps = Readonly<Required<{
    property: Property;
}>>;

const defaultProps: DefaultProps = {
    property: {
        accessibility: Accessibility.API,
        documentationContent: "<b>Some</b> documentation content",
        name: "fooProperty",
        namespace: "foo.bar.namespace",
        qualifiedName: "foo.bar.namespace.fooProperty",
    },
};

//
// Class
//
export default class PropertyDoc extends React.PureComponent<IPropertyDocProps, any> {
    public static readonly defaultProps = defaultProps;

    public render() {
        return (
            <div className="property-doc">
                <div className="property-doc--icon">
                    <FontAwesomeIcon icon={iconForProperty(this.props.property)} />
                </div>
                <div className="property-doc--title">
                    <label>Property</label>
                    <div className="property-doc--title--name">{this.props.property.name}</div>
                </div>
                <div className="property-doc--documentation">
                    <label>Documentation</label>
                    <div dangerouslySetInnerHTML={{ __html: this.props.property.documentationContent }} className="property-doc--documentation--html" />
                </div>
            </div>
        );
    }

}
