import * as React from 'react';

import { Accessibility } from '../../../Domain/Accessibility';
import Property from '../../../Domain/Property';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { iconForProperty } from "../../../icons";

// Props
//
interface IPropertyDocProps {
    readonly property: Property;
    readonly showSpringHowTo: boolean;
    // actions
}

type DefaultProps = Readonly<Required<{
    property: Property;
    showSpringHowTo: boolean;
}>>;

const defaultProps: DefaultProps = {
    property: {
        accessibility: Accessibility.API,
        documentationContent: "<b>Some</b> documentation content",
        name: "fooProperty",
        namespace: "foo.bar.namespace",
        qualifiedName: "foo.bar.namespace.fooProperty",
        type: {
            required: false,
            typeName: "java.lang.String"
        }
    },
    showSpringHowTo: true
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
                    <label>Type</label>
                    <div>{this.props.property.type.typeName} (required: {this.props.property.type.required ? 'true' : 'false'})</div>
                    <label>Documentation</label>
                    <div dangerouslySetInnerHTML={{ __html: this.props.property.documentationContent }} className="property-doc--documentation--html" />
                </div>
                <div className="property-doc--how-to-configure">
                    {this.props.showSpringHowTo ? (
                        <div className="property-doc--how-to-spring">
                            <label>How to configure with Spring</label>
                            <h4>Set the property via application yaml:</h4>
                            <p className="how-to-configure--yaml">
                                <pre>
                                    {this.props.property.namespace + ":\n"}
                                    &nbsp;&nbsp;{this.props.property.name}: [VALUE]                                
                                </pre>
                            </p>
                            <p>
                                Note, that the indent of the yaml - 2 spaces, no tabs - is important!
                                This is just an example, and the real application.yml may differ in its key grouping.
                            </p>
                            <h4>Set the property via environment variable:</h4>
                            <p className="how-to-configure--env">
                                <pre>
                                    {this.buildEnvironmentVarName()}=[VALUE]
                                </pre>
                            </p>
                            {this.props.property.namespace.match("<KEY>") ? (<p>replace <b>&lt;KEY&gt;</b> with the configuration item name</p>) : ''}
                            {this.props.property.namespace.match("<INDEX>") ? (<p>replace <b>&lt;INDEX&gt;</b> with the list position (0-based, integer)</p>) : ''}
                        </div>
                    ) : ''}
                </div>
            </div>
        );
    }

    private buildEnvironmentVarName() {
        return this.props.property.qualifiedName
            .replace(new RegExp("\\.", 'g'), "_")
            .toUpperCase()
    }
}