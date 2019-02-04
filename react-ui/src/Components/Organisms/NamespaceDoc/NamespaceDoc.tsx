import * as React from 'react';

import Namespace from '../../../Domain/Namespace';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { iconForNamespace } from "../../../icons";


//
// Props
//
interface INamespaceDocProps {
    readonly namespace: Namespace;

    // actions
}

type DefaultProps = Readonly<Required<{
    namespace: Namespace;
}>>;

const defaultProps: DefaultProps = {
    namespace: {
        documentationContent: "Some <b>Namespace</b> documentation content",
        name: "foo.bar.namespace",
    },
};

//
// Class
//
export default class NamespaceDoc extends React.PureComponent<INamespaceDocProps, any> {
    public static readonly defaultProps = defaultProps;

    public render() {
        return (
            <div className="namespace-doc">
                <div className="namespace-doc--icon">
                    <FontAwesomeIcon icon={iconForNamespace(this.props.namespace)} />
                </div>
                <div className="namespace-doc--title">
                    <label>Namespace</label>
                    <div className="namespace-doc--title--name">{this.props.namespace.name}</div>
                </div>
                <div className="namespace-doc--documentation">
                    <label>Documentation</label>
                    <div dangerouslySetInnerHTML={{ __html: this.props.namespace.documentationContent }} className="namespace-doc--documentation--html" />
                </div>
            </div>
        );
    }

}
