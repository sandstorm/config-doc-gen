import * as React from 'react';
import {render} from 'react-dom';
import * as ReactTestRenderer from 'react-test-renderer';
import SidebarPropertyList from '.';
import {Accessibility} from "../../../Domain/Accessibility";

describe('SidebarPropertyList tests', () => {
    it('should render without crashing', () => {
        render(<SidebarPropertyList/>, document.createElement('div'));
    });

    it('should match snapshot', () => {
        expect(ReactTestRenderer.create(<SidebarPropertyList/>)).toMatchSnapshot();
    });

    it('should match snapshot with template props', () => {
        expect(ReactTestRenderer.create(<SidebarPropertyList properties={[
            {
                name: "prop",
                qualifiedName: "foo.bar.prop",
                namespace: "foo.bar",
                accessibility: Accessibility.API,
                documentation: "doc comments 1 ... <br/> supports HTML"
            },
            {
                name: "prop2",
                qualifiedName: "foo.bar.prop2",
                namespace: "foo.bar",
                accessibility: Accessibility.IMPLEMENTATION,
                documentation: "doc comments 2 ... <br/> supports HTML"
            },
            {
                name: "prop",
                qualifiedName: "foo.bar.<KEY>.namespaceName.<INDEX>.prop",
                namespace: "foo.bar.<KEY>.namespaceName.<INDEX>",
                accessibility: Accessibility.API,
                documentation: "doc comments 3 ... <br/> supports HTML"
            }
        ]}/>)).toMatchSnapshot();
    });
});
