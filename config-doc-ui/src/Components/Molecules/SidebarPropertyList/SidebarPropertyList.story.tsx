import { storiesOf } from '@storybook/react';
import * as React from 'react';
import SidebarPropertyList from '.';
import {Accessibility} from "../../../Domain/Accessibility";

storiesOf('SidebarPropertyList', module)
  .add('default', () => (
    <SidebarPropertyList />
  ))
  .add('with template prop', () => (
    <SidebarPropertyList properties={[
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
    ]}/>
  ));
