package com.horstmann.violet.product.diagram.classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.edge.AggregationEdge;
import com.horstmann.violet.product.diagram.classes.edge.AssociationEdge;
import com.horstmann.violet.product.diagram.classes.edge.CompositionEdge;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;

public class ClassDiagramGraphTest {

	INode startNode, endNode;
	IEdge edge1, edge2;
	ClassDiagramGraph classDiagram = new ClassDiagramGraph();

	@Before
	public void setUp() {
		startNode = new ClassNode();
		endNode = new ClassNode();
		edge1 = new AggregationEdge();
		edge2 = new AggregationEdge();
	}

	@Test
	public void test_isBidirectionalRelationAllowed_false_1() {
		classDiagram.connect(edge1, startNode, null, endNode, null, null);
		classDiagram.connect(edge2, endNode, null, startNode, null, null);
		Assert.assertFalse(classDiagram.isBidirectionalRelationAllowed(startNode, endNode));
	}
	
	@Test
	public void test_isBidirectionalRelationAllowed_false_2() {
		edge2 = new CompositionEdge();
		classDiagram.connect(edge1, startNode, null, endNode, null, null);
		classDiagram.connect(edge2, endNode, null, startNode, null, null);
		Assert.assertFalse(classDiagram.isBidirectionalRelationAllowed(startNode, endNode));
	}

	@Test
	public void test_isBidirectionalRelationAllowed_true() {
		classDiagram.connect(edge1, startNode, null, endNode, null, null);
		Assert.assertTrue(classDiagram.isBidirectionalRelationAllowed(startNode, endNode));
	}
	
	@Test
	public void test_isBidirectionalRelationExist_true() {
		classDiagram.connect(edge1, startNode, null, endNode, null, null);
		classDiagram.connect(edge2, endNode, null, startNode, null, null);
		Assert.assertTrue(classDiagram.isBidirectionalRelationExist());
	}
	
	@Test
	public void test_isBidirectionalRelationExist_false_1() {
		classDiagram.connect(edge1, startNode, null, endNode, null, null);
		Assert.assertFalse(classDiagram.isBidirectionalRelationExist());
	}
	
	@Test
	public void test_isBidirectionalRelationExist_false_2() {
		classDiagram.connect(edge1, startNode, null, startNode, null, null);
		Assert.assertFalse(classDiagram.isBidirectionalRelationExist());
	}
	
	@Test
	public void test_isBidirectionalRelationExist_false_3() {
		edge1 = new AssociationEdge();
		classDiagram.connect(edge1, startNode, null, endNode, null, null);
		Assert.assertFalse(classDiagram.isBidirectionalRelationExist());
	}
}
