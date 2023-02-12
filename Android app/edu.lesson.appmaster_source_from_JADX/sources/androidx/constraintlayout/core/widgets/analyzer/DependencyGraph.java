package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class DependencyGraph {
    private static final boolean USE_GROUPS = true;
    private ConstraintWidgetContainer container;
    private ConstraintWidgetContainer mContainer;
    ArrayList<RunGroup> mGroups = new ArrayList<>();
    private BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    private BasicMeasure.Measurer mMeasurer = null;
    private boolean mNeedBuildGraph = true;
    private boolean mNeedRedoMeasures = true;
    private ArrayList<WidgetRun> mRuns = new ArrayList<>();
    private ArrayList<RunGroup> runGroups = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer container2) {
        this.container = container2;
        this.mContainer = container2;
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
    }

    private int computeWrap(ConstraintWidgetContainer container2, int orientation) {
        int count = this.mGroups.size();
        long wrapSize = 0;
        for (int i = 0; i < count; i++) {
            wrapSize = Math.max(wrapSize, this.mGroups.get(i).computeWrapSize(container2, orientation));
        }
        return (int) wrapSize;
    }

    public void defineTerminalWidgets(ConstraintWidget.DimensionBehaviour horizontalBehavior, ConstraintWidget.DimensionBehaviour verticalBehavior) {
        if (this.mNeedBuildGraph) {
            buildGraph();
            boolean hasBarrier = false;
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget widget = (ConstraintWidget) it.next();
                widget.isTerminalWidget[0] = true;
                widget.isTerminalWidget[1] = true;
                if (widget instanceof Barrier) {
                    hasBarrier = true;
                }
            }
            if (!hasBarrier) {
                Iterator<RunGroup> it2 = this.mGroups.iterator();
                while (it2.hasNext()) {
                    it2.next().defineTerminalWidgets(horizontalBehavior == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, verticalBehavior == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                }
            }
        }
    }

    public boolean directMeasure(boolean optimizeWrap) {
        boolean optimizeWrap2 = optimizeWrap & true;
        if (this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget widget = (ConstraintWidget) it.next();
                widget.ensureWidgetRuns();
                widget.measured = false;
                widget.horizontalRun.reset();
                widget.verticalRun.reset();
            }
            this.container.ensureWidgetRuns();
            this.container.measured = false;
            this.container.horizontalRun.reset();
            this.container.verticalRun.reset();
            this.mNeedRedoMeasures = false;
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        ConstraintWidget.DimensionBehaviour originalHorizontalDimension = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour originalVerticalDimension = this.container.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph) {
            buildGraph();
        }
        int x1 = this.container.getX();
        int y1 = this.container.getY();
        this.container.horizontalRun.start.resolve(x1);
        this.container.verticalRun.start.resolve(y1);
        measureWidgets();
        if (originalHorizontalDimension == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || originalVerticalDimension == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            if (optimizeWrap2) {
                Iterator<WidgetRun> it2 = this.mRuns.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!it2.next().supportsWrapComputation()) {
                            optimizeWrap2 = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (optimizeWrap2 && originalHorizontalDimension == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer = this.container;
                constraintWidgetContainer.setWidth(computeWrap(constraintWidgetContainer, 0));
                this.container.horizontalRun.dimension.resolve(this.container.getWidth());
            }
            if (optimizeWrap2 && originalVerticalDimension == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                constraintWidgetContainer2.setHeight(computeWrap(constraintWidgetContainer2, 1));
                this.container.verticalRun.dimension.resolve(this.container.getHeight());
            }
        }
        boolean checkRoot = false;
        if (this.container.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || this.container.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int x2 = this.container.getWidth() + x1;
            this.container.horizontalRun.end.resolve(x2);
            this.container.horizontalRun.dimension.resolve(x2 - x1);
            measureWidgets();
            if (this.container.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || this.container.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int y2 = this.container.getHeight() + y1;
                this.container.verticalRun.end.resolve(y2);
                this.container.verticalRun.dimension.resolve(y2 - y1);
            }
            measureWidgets();
            checkRoot = true;
        }
        Iterator<WidgetRun> it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun run = it3.next();
            if (run.widget != this.container || run.resolved) {
                run.applyToWidget();
            }
        }
        boolean allResolved = true;
        Iterator<WidgetRun> it4 = this.mRuns.iterator();
        while (true) {
            if (!it4.hasNext()) {
                break;
            }
            WidgetRun run2 = it4.next();
            if (checkRoot || run2.widget != this.container) {
                if (run2.start.resolved) {
                    if (run2.end.resolved || (run2 instanceof GuidelineReference)) {
                        if (!run2.dimension.resolved && !(run2 instanceof ChainRun) && !(run2 instanceof GuidelineReference)) {
                            allResolved = false;
                            break;
                        }
                    } else {
                        allResolved = false;
                        break;
                    }
                } else {
                    allResolved = false;
                    break;
                }
            }
        }
        this.container.setHorizontalDimensionBehaviour(originalHorizontalDimension);
        this.container.setVerticalDimensionBehaviour(originalVerticalDimension);
        return allResolved;
    }

    public boolean directMeasureSetup(boolean optimizeWrap) {
        if (this.mNeedBuildGraph) {
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget widget = (ConstraintWidget) it.next();
                widget.ensureWidgetRuns();
                widget.measured = false;
                widget.horizontalRun.dimension.resolved = false;
                widget.horizontalRun.resolved = false;
                widget.horizontalRun.reset();
                widget.verticalRun.dimension.resolved = false;
                widget.verticalRun.resolved = false;
                widget.verticalRun.reset();
            }
            this.container.ensureWidgetRuns();
            this.container.measured = false;
            this.container.horizontalRun.dimension.resolved = false;
            this.container.horizontalRun.resolved = false;
            this.container.horizontalRun.reset();
            this.container.verticalRun.dimension.resolved = false;
            this.container.verticalRun.resolved = false;
            this.container.verticalRun.reset();
            buildGraph();
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        this.container.horizontalRun.start.resolve(0);
        this.container.verticalRun.start.resolve(0);
        return true;
    }

    public boolean directMeasureWithOrientation(boolean optimizeWrap, int orientation) {
        boolean optimizeWrap2 = optimizeWrap & true;
        ConstraintWidget.DimensionBehaviour originalHorizontalDimension = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour originalVerticalDimension = this.container.getDimensionBehaviour(1);
        int x1 = this.container.getX();
        int y1 = this.container.getY();
        if (optimizeWrap2 && (originalHorizontalDimension == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || originalVerticalDimension == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
            Iterator<WidgetRun> it = this.mRuns.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun run = it.next();
                if (run.orientation == orientation && !run.supportsWrapComputation()) {
                    optimizeWrap2 = false;
                    break;
                }
            }
            if (orientation == 0) {
                if (optimizeWrap2 && originalHorizontalDimension == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    ConstraintWidgetContainer constraintWidgetContainer = this.container;
                    constraintWidgetContainer.setWidth(computeWrap(constraintWidgetContainer, 0));
                    this.container.horizontalRun.dimension.resolve(this.container.getWidth());
                }
            } else if (optimizeWrap2 && originalVerticalDimension == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                constraintWidgetContainer2.setHeight(computeWrap(constraintWidgetContainer2, 1));
                this.container.verticalRun.dimension.resolve(this.container.getHeight());
            }
        }
        boolean checkRoot = false;
        if (orientation == 0) {
            if (this.container.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || this.container.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int x2 = this.container.getWidth() + x1;
                this.container.horizontalRun.end.resolve(x2);
                this.container.horizontalRun.dimension.resolve(x2 - x1);
                checkRoot = true;
            }
        } else if (this.container.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || this.container.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int y2 = this.container.getHeight() + y1;
            this.container.verticalRun.end.resolve(y2);
            this.container.verticalRun.dimension.resolve(y2 - y1);
            checkRoot = true;
        }
        measureWidgets();
        Iterator<WidgetRun> it2 = this.mRuns.iterator();
        while (it2.hasNext()) {
            WidgetRun run2 = it2.next();
            if (run2.orientation == orientation && (run2.widget != this.container || run2.resolved)) {
                run2.applyToWidget();
            }
        }
        boolean allResolved = true;
        Iterator<WidgetRun> it3 = this.mRuns.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            WidgetRun run3 = it3.next();
            if (run3.orientation == orientation && (checkRoot || run3.widget != this.container)) {
                if (run3.start.resolved) {
                    if (run3.end.resolved) {
                        if (!(run3 instanceof ChainRun) && !run3.dimension.resolved) {
                            allResolved = false;
                            break;
                        }
                    } else {
                        allResolved = false;
                        break;
                    }
                } else {
                    allResolved = false;
                    break;
                }
            }
        }
        this.container.setHorizontalDimensionBehaviour(originalHorizontalDimension);
        this.container.setVerticalDimensionBehaviour(originalVerticalDimension);
        return allResolved;
    }

    private void measure(ConstraintWidget widget, ConstraintWidget.DimensionBehaviour horizontalBehavior, int horizontalDimension, ConstraintWidget.DimensionBehaviour verticalBehavior, int verticalDimension) {
        this.mMeasure.horizontalBehavior = horizontalBehavior;
        this.mMeasure.verticalBehavior = verticalBehavior;
        this.mMeasure.horizontalDimension = horizontalDimension;
        this.mMeasure.verticalDimension = verticalDimension;
        this.mMeasurer.measure(widget, this.mMeasure);
        widget.setWidth(this.mMeasure.measuredWidth);
        widget.setHeight(this.mMeasure.measuredHeight);
        widget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        widget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    private boolean basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer) {
        ConstraintWidget.DimensionBehaviour horizontal;
        ConstraintWidget.DimensionBehaviour vertical;
        ConstraintWidgetContainer constraintWidgetContainer2 = constraintWidgetContainer;
        Iterator it = constraintWidgetContainer2.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget widget = (ConstraintWidget) it.next();
            ConstraintWidget.DimensionBehaviour horizontal2 = widget.mListDimensionBehaviors[0];
            ConstraintWidget.DimensionBehaviour vertical2 = widget.mListDimensionBehaviors[1];
            if (widget.getVisibility() == 8) {
                widget.measured = true;
            } else {
                if (widget.mMatchConstraintPercentWidth < 1.0f && horizontal2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    widget.mMatchConstraintDefaultWidth = 2;
                }
                if (widget.mMatchConstraintPercentHeight < 1.0f && vertical2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    widget.mMatchConstraintDefaultHeight = 2;
                }
                if (widget.getDimensionRatio() > 0.0f) {
                    if (horizontal2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (vertical2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || vertical2 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        widget.mMatchConstraintDefaultWidth = 3;
                    } else if (vertical2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (horizontal2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || horizontal2 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        widget.mMatchConstraintDefaultHeight = 3;
                    } else if (horizontal2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && vertical2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        if (widget.mMatchConstraintDefaultWidth == 0) {
                            widget.mMatchConstraintDefaultWidth = 3;
                        }
                        if (widget.mMatchConstraintDefaultHeight == 0) {
                            widget.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                if (horizontal2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mMatchConstraintDefaultWidth == 1 && (widget.mLeft.mTarget == null || widget.mRight.mTarget == null)) {
                    horizontal = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                } else {
                    horizontal = horizontal2;
                }
                if (vertical2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mMatchConstraintDefaultHeight == 1 && (widget.mTop.mTarget == null || widget.mBottom.mTarget == null)) {
                    vertical = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                } else {
                    vertical = vertical2;
                }
                widget.horizontalRun.dimensionBehavior = horizontal;
                widget.horizontalRun.matchConstraintsType = widget.mMatchConstraintDefaultWidth;
                widget.verticalRun.dimensionBehavior = vertical;
                widget.verticalRun.matchConstraintsType = widget.mMatchConstraintDefaultHeight;
                if ((horizontal == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || horizontal == ConstraintWidget.DimensionBehaviour.FIXED || horizontal == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (vertical == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || vertical == ConstraintWidget.DimensionBehaviour.FIXED || vertical == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int width = widget.getWidth();
                    if (horizontal == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                        width = (constraintWidgetContainer.getWidth() - widget.mLeft.mMargin) - widget.mRight.mMargin;
                        horizontal = ConstraintWidget.DimensionBehaviour.FIXED;
                    }
                    int height = widget.getHeight();
                    if (vertical == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                        height = (constraintWidgetContainer.getHeight() - widget.mTop.mMargin) - widget.mBottom.mMargin;
                        vertical = ConstraintWidget.DimensionBehaviour.FIXED;
                    }
                    measure(widget, horizontal, width, vertical, height);
                    widget.horizontalRun.dimension.resolve(widget.getWidth());
                    widget.verticalRun.dimension.resolve(widget.getHeight());
                    widget.measured = true;
                } else {
                    if (horizontal == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (vertical == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || vertical == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (widget.mMatchConstraintDefaultWidth == 3) {
                            if (vertical == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                                measure(widget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            }
                            int height2 = widget.getHeight();
                            measure(widget, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((((float) height2) * widget.mDimensionRatio) + 0.5f), ConstraintWidget.DimensionBehaviour.FIXED, height2);
                            widget.horizontalRun.dimension.resolve(widget.getWidth());
                            widget.verticalRun.dimension.resolve(widget.getHeight());
                            widget.measured = true;
                        } else if (widget.mMatchConstraintDefaultWidth == 1) {
                            measure(widget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, vertical, 0);
                            widget.horizontalRun.dimension.wrapValue = widget.getWidth();
                        } else if (widget.mMatchConstraintDefaultWidth == 2) {
                            if (constraintWidgetContainer2.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || constraintWidgetContainer2.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                                ConstraintWidget constraintWidget = widget;
                                measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((((float) constraintWidgetContainer.getWidth()) * widget.mMatchConstraintPercentWidth) + 0.5f), vertical, widget.getHeight());
                                widget.horizontalRun.dimension.resolve(widget.getWidth());
                                widget.verticalRun.dimension.resolve(widget.getHeight());
                                widget.measured = true;
                            }
                        } else if (widget.mListAnchors[0].mTarget == null || widget.mListAnchors[1].mTarget == null) {
                            measure(widget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, vertical, 0);
                            widget.horizontalRun.dimension.resolve(widget.getWidth());
                            widget.verticalRun.dimension.resolve(widget.getHeight());
                            widget.measured = true;
                        }
                    }
                    if (vertical == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (horizontal == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || horizontal == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (widget.mMatchConstraintDefaultHeight == 3) {
                            if (horizontal == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                                measure(widget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            }
                            int width2 = widget.getWidth();
                            float ratio = widget.mDimensionRatio;
                            if (widget.getDimensionRatioSide() == -1) {
                                ratio = 1.0f / ratio;
                            }
                            ConstraintWidget constraintWidget2 = widget;
                            int i = width2;
                            measure(constraintWidget2, ConstraintWidget.DimensionBehaviour.FIXED, i, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((((float) width2) * ratio) + 0.5f));
                            widget.horizontalRun.dimension.resolve(widget.getWidth());
                            widget.verticalRun.dimension.resolve(widget.getHeight());
                            widget.measured = true;
                        } else if (widget.mMatchConstraintDefaultHeight == 1) {
                            measure(widget, horizontal, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            widget.verticalRun.dimension.wrapValue = widget.getHeight();
                        } else if (widget.mMatchConstraintDefaultHeight == 2) {
                            if (constraintWidgetContainer2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || constraintWidgetContainer2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                                float percent = widget.mMatchConstraintPercentHeight;
                                int width3 = widget.getWidth();
                                ConstraintWidget constraintWidget3 = widget;
                                ConstraintWidget.DimensionBehaviour dimensionBehaviour = horizontal;
                                int i2 = width3;
                                measure(constraintWidget3, dimensionBehaviour, i2, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((((float) constraintWidgetContainer.getHeight()) * percent) + 0.5f));
                                widget.horizontalRun.dimension.resolve(widget.getWidth());
                                widget.verticalRun.dimension.resolve(widget.getHeight());
                                widget.measured = true;
                            }
                        } else if (widget.mListAnchors[2].mTarget == null || widget.mListAnchors[3].mTarget == null) {
                            measure(widget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, vertical, 0);
                            widget.horizontalRun.dimension.resolve(widget.getWidth());
                            widget.verticalRun.dimension.resolve(widget.getHeight());
                            widget.measured = true;
                        }
                    }
                    if (horizontal == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && vertical == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        if (widget.mMatchConstraintDefaultWidth == 1 || widget.mMatchConstraintDefaultHeight == 1) {
                            measure(widget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            widget.horizontalRun.dimension.wrapValue = widget.getWidth();
                            widget.verticalRun.dimension.wrapValue = widget.getHeight();
                        } else if (widget.mMatchConstraintDefaultHeight == 2 && widget.mMatchConstraintDefaultWidth == 2 && constraintWidgetContainer2.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED && constraintWidgetContainer2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED) {
                            float horizPercent = widget.mMatchConstraintPercentWidth;
                            float vertPercent = widget.mMatchConstraintPercentHeight;
                            int width4 = (int) ((((float) constraintWidgetContainer.getWidth()) * horizPercent) + 0.5f);
                            ConstraintWidget constraintWidget4 = widget;
                            int i3 = width4;
                            measure(constraintWidget4, ConstraintWidget.DimensionBehaviour.FIXED, i3, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((((float) constraintWidgetContainer.getHeight()) * vertPercent) + 0.5f));
                            widget.horizontalRun.dimension.resolve(widget.getWidth());
                            widget.verticalRun.dimension.resolve(widget.getHeight());
                            widget.measured = true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void measureWidgets() {
        Iterator it = this.container.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget widget = (ConstraintWidget) it.next();
            if (!widget.measured) {
                boolean z = false;
                ConstraintWidget.DimensionBehaviour horiz = widget.mListDimensionBehaviors[0];
                ConstraintWidget.DimensionBehaviour vert = widget.mListDimensionBehaviors[1];
                int horizMatchConstraintsType = widget.mMatchConstraintDefaultWidth;
                int vertMatchConstraintsType = widget.mMatchConstraintDefaultHeight;
                boolean horizWrap = horiz == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (horiz == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && horizMatchConstraintsType == 1);
                if (vert == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (vert == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && vertMatchConstraintsType == 1)) {
                    z = true;
                }
                boolean vertWrap = z;
                boolean horizResolved = widget.horizontalRun.dimension.resolved;
                boolean vertResolved = widget.verticalRun.dimension.resolved;
                if (!horizResolved || !vertResolved) {
                    boolean vertResolved2 = vertResolved;
                    if (horizResolved && vertWrap) {
                        measure(widget, ConstraintWidget.DimensionBehaviour.FIXED, widget.horizontalRun.dimension.value, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, widget.verticalRun.dimension.value);
                        if (vert == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                            widget.verticalRun.dimension.wrapValue = widget.getHeight();
                        } else {
                            widget.verticalRun.dimension.resolve(widget.getHeight());
                            widget.measured = true;
                        }
                    } else if (vertResolved2 && horizWrap) {
                        measure(widget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, widget.horizontalRun.dimension.value, ConstraintWidget.DimensionBehaviour.FIXED, widget.verticalRun.dimension.value);
                        if (horiz == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                            widget.horizontalRun.dimension.wrapValue = widget.getWidth();
                        } else {
                            widget.horizontalRun.dimension.resolve(widget.getWidth());
                            widget.measured = true;
                        }
                    }
                } else {
                    boolean z2 = vertResolved;
                    boolean z3 = horizResolved;
                    measure(widget, ConstraintWidget.DimensionBehaviour.FIXED, widget.horizontalRun.dimension.value, ConstraintWidget.DimensionBehaviour.FIXED, widget.verticalRun.dimension.value);
                    widget.measured = true;
                }
                if (widget.measured && widget.verticalRun.baselineDimension != null) {
                    widget.verticalRun.baselineDimension.resolve(widget.getBaselineDistance());
                }
            }
        }
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.container.horizontalRun, 0, this.mGroups);
        findGroup(this.container.verticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public void buildGraph(ArrayList<WidgetRun> runs) {
        runs.clear();
        this.mContainer.horizontalRun.clear();
        this.mContainer.verticalRun.clear();
        runs.add(this.mContainer.horizontalRun);
        runs.add(this.mContainer.verticalRun);
        HashSet<ChainRun> chainRuns = null;
        Iterator it = this.mContainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget widget = (ConstraintWidget) it.next();
            if (widget instanceof Guideline) {
                runs.add(new GuidelineReference(widget));
            } else {
                if (widget.isInHorizontalChain()) {
                    if (widget.horizontalChainRun == null) {
                        widget.horizontalChainRun = new ChainRun(widget, 0);
                    }
                    if (chainRuns == null) {
                        chainRuns = new HashSet<>();
                    }
                    chainRuns.add(widget.horizontalChainRun);
                } else {
                    runs.add(widget.horizontalRun);
                }
                if (widget.isInVerticalChain()) {
                    if (widget.verticalChainRun == null) {
                        widget.verticalChainRun = new ChainRun(widget, 1);
                    }
                    if (chainRuns == null) {
                        chainRuns = new HashSet<>();
                    }
                    chainRuns.add(widget.verticalChainRun);
                } else {
                    runs.add(widget.verticalRun);
                }
                if (widget instanceof HelperWidget) {
                    runs.add(new HelperReferences(widget));
                }
            }
        }
        if (chainRuns != null) {
            runs.addAll(chainRuns);
        }
        Iterator<WidgetRun> it2 = runs.iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
        Iterator<WidgetRun> it3 = runs.iterator();
        while (it3.hasNext()) {
            WidgetRun run = it3.next();
            if (run.widget != this.mContainer) {
                run.apply();
            }
        }
    }

    private void displayGraph() {
        String content = "digraph {\n";
        Iterator<WidgetRun> it = this.mRuns.iterator();
        while (it.hasNext()) {
            content = generateDisplayGraph(it.next(), content);
        }
        System.out.println("content:<<\n" + (content + "\n}\n") + "\n>>");
    }

    private void applyGroup(DependencyNode node, int orientation, int direction, DependencyNode end, ArrayList<RunGroup> groups, RunGroup group) {
        RunGroup group2;
        int i = orientation;
        DependencyNode dependencyNode = end;
        WidgetRun run = node.run;
        if (run.runGroup != null || run == this.container.horizontalRun) {
            int i2 = direction;
            ArrayList<RunGroup> arrayList = groups;
        } else if (run == this.container.verticalRun) {
            int i3 = direction;
            ArrayList<RunGroup> arrayList2 = groups;
        } else {
            if (group == null) {
                RunGroup group3 = new RunGroup(run, direction);
                groups.add(group3);
                group2 = group3;
            } else {
                int i4 = direction;
                ArrayList<RunGroup> arrayList3 = groups;
                group2 = group;
            }
            run.runGroup = group2;
            group2.add(run);
            for (Dependency dependent : run.start.dependencies) {
                if (dependent instanceof DependencyNode) {
                    Dependency dependency = dependent;
                    applyGroup((DependencyNode) dependent, orientation, 0, end, groups, group2);
                }
            }
            for (Dependency dependent2 : run.end.dependencies) {
                if (dependent2 instanceof DependencyNode) {
                    Dependency dependency2 = dependent2;
                    applyGroup((DependencyNode) dependent2, orientation, 1, end, groups, group2);
                }
            }
            if (i == 1 && (run instanceof VerticalWidgetRun)) {
                for (Dependency dependent3 : ((VerticalWidgetRun) run).baseline.dependencies) {
                    if (dependent3 instanceof DependencyNode) {
                        Dependency dependency3 = dependent3;
                        applyGroup((DependencyNode) dependent3, orientation, 2, end, groups, group2);
                    }
                }
            }
            for (DependencyNode target : run.start.targets) {
                if (target == dependencyNode) {
                    group2.dual = true;
                }
                DependencyNode dependencyNode2 = target;
                applyGroup(target, orientation, 0, end, groups, group2);
            }
            for (DependencyNode target2 : run.end.targets) {
                if (target2 == dependencyNode) {
                    group2.dual = true;
                }
                DependencyNode dependencyNode3 = target2;
                applyGroup(target2, orientation, 1, end, groups, group2);
            }
            if (i == 1 && (run instanceof VerticalWidgetRun)) {
                for (DependencyNode target3 : ((VerticalWidgetRun) run).baseline.targets) {
                    applyGroup(target3, orientation, 2, end, groups, group2);
                }
            }
        }
    }

    private void findGroup(WidgetRun run, int orientation, ArrayList<RunGroup> groups) {
        for (Dependency dependent : run.start.dependencies) {
            if (dependent instanceof DependencyNode) {
                applyGroup((DependencyNode) dependent, orientation, 0, run.end, groups, (RunGroup) null);
            } else if (dependent instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependent).start, orientation, 0, run.end, groups, (RunGroup) null);
            }
        }
        for (Dependency dependent2 : run.end.dependencies) {
            if (dependent2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependent2, orientation, 1, run.start, groups, (RunGroup) null);
            } else if (dependent2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependent2).end, orientation, 1, run.start, groups, (RunGroup) null);
            }
        }
        if (orientation == 1) {
            for (Dependency dependent3 : ((VerticalWidgetRun) run).baseline.dependencies) {
                if (dependent3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependent3, orientation, 2, (DependencyNode) null, groups, (RunGroup) null);
                }
            }
        }
    }

    private String generateDisplayNode(DependencyNode node, boolean centeredConnection, String content) {
        StringBuilder contentBuilder = new StringBuilder(content);
        for (DependencyNode target : node.targets) {
            String constraint = ("\n" + node.name()) + " -> " + target.name();
            if (node.margin > 0 || centeredConnection || (node.run instanceof HelperReferences)) {
                String constraint2 = constraint + "[";
                if (node.margin > 0) {
                    constraint2 = constraint2 + "label=\"" + node.margin + "\"";
                    if (centeredConnection) {
                        constraint2 = constraint2 + ",";
                    }
                }
                if (centeredConnection) {
                    constraint2 = constraint2 + " style=dashed ";
                }
                if (node.run instanceof HelperReferences) {
                    constraint2 = constraint2 + " style=bold,color=gray ";
                }
                constraint = constraint2 + "]";
            }
            contentBuilder.append(constraint + "\n");
        }
        return contentBuilder.toString();
    }

    private String nodeDefinition(WidgetRun run) {
        ConstraintWidget.DimensionBehaviour behaviour;
        int orientation = run instanceof VerticalWidgetRun;
        String name = run.widget.getDebugName();
        StringBuilder definition = new StringBuilder(name);
        ConstraintWidget constraintWidget = run.widget;
        if (orientation == 0) {
            behaviour = constraintWidget.getHorizontalDimensionBehaviour();
        } else {
            behaviour = constraintWidget.getVerticalDimensionBehaviour();
        }
        RunGroup runGroup = run.runGroup;
        if (orientation == 0) {
            definition.append("_HORIZONTAL");
        } else {
            definition.append("_VERTICAL");
        }
        definition.append(" [shape=none, label=<");
        definition.append("<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"2\">");
        definition.append("  <TR>");
        if (orientation == 0) {
            definition.append("    <TD ");
            if (run.start.resolved) {
                definition.append(" BGCOLOR=\"green\"");
            }
            definition.append(" PORT=\"LEFT\" BORDER=\"1\">L</TD>");
        } else {
            definition.append("    <TD ");
            if (run.start.resolved) {
                definition.append(" BGCOLOR=\"green\"");
            }
            definition.append(" PORT=\"TOP\" BORDER=\"1\">T</TD>");
        }
        definition.append("    <TD BORDER=\"1\" ");
        if (run.dimension.resolved && !run.widget.measured) {
            definition.append(" BGCOLOR=\"green\" ");
        } else if (run.dimension.resolved) {
            definition.append(" BGCOLOR=\"lightgray\" ");
        } else if (run.widget.measured) {
            definition.append(" BGCOLOR=\"yellow\" ");
        }
        if (behaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            definition.append("style=\"dashed\"");
        }
        definition.append(">");
        definition.append(name);
        if (runGroup != null) {
            definition.append(" [");
            definition.append(runGroup.groupIndex + 1);
            definition.append("/");
            definition.append(RunGroup.index);
            definition.append("]");
        }
        definition.append(" </TD>");
        if (orientation == 0) {
            definition.append("    <TD ");
            if (run.end.resolved) {
                definition.append(" BGCOLOR=\"green\"");
            }
            definition.append(" PORT=\"RIGHT\" BORDER=\"1\">R</TD>");
        } else {
            definition.append("    <TD ");
            if (((VerticalWidgetRun) run).baseline.resolved) {
                definition.append(" BGCOLOR=\"green\"");
            }
            definition.append(" PORT=\"BASELINE\" BORDER=\"1\">b</TD>");
            definition.append("    <TD ");
            if (run.end.resolved) {
                definition.append(" BGCOLOR=\"green\"");
            }
            definition.append(" PORT=\"BOTTOM\" BORDER=\"1\">B</TD>");
        }
        definition.append("  </TR></TABLE>");
        definition.append(">];\n");
        return definition.toString();
    }

    private String generateChainDisplayGraph(ChainRun chain, String content) {
        int orientation = chain.orientation;
        StringBuilder subgroup = new StringBuilder("subgraph ");
        subgroup.append("cluster_");
        subgroup.append(chain.widget.getDebugName());
        if (orientation == 0) {
            subgroup.append("_h");
        } else {
            subgroup.append("_v");
        }
        subgroup.append(" {\n");
        String definitions = "";
        Iterator<WidgetRun> it = chain.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun run = it.next();
            subgroup.append(run.widget.getDebugName());
            if (orientation == 0) {
                subgroup.append("_HORIZONTAL");
            } else {
                subgroup.append("_VERTICAL");
            }
            subgroup.append(";\n");
            definitions = generateDisplayGraph(run, definitions);
        }
        subgroup.append("}\n");
        return content + definitions + subgroup;
    }

    private boolean isCenteredConnection(DependencyNode start, DependencyNode end) {
        int startTargets = 0;
        int endTargets = 0;
        for (DependencyNode s : start.targets) {
            if (s != end) {
                startTargets++;
            }
        }
        for (DependencyNode e : end.targets) {
            if (e != start) {
                endTargets++;
            }
        }
        return startTargets > 0 && endTargets > 0;
    }

    private String generateDisplayGraph(WidgetRun root, String content) {
        DependencyNode start = root.start;
        DependencyNode end = root.end;
        StringBuilder sb = new StringBuilder(content);
        if (!(root instanceof HelperReferences) && start.dependencies.isEmpty() && (end.dependencies.isEmpty() && start.targets.isEmpty()) && end.targets.isEmpty()) {
            return content;
        }
        sb.append(nodeDefinition(root));
        boolean centeredConnection = isCenteredConnection(start, end);
        String content2 = generateDisplayNode(end, centeredConnection, generateDisplayNode(start, centeredConnection, content));
        if (root instanceof VerticalWidgetRun) {
            content2 = generateDisplayNode(((VerticalWidgetRun) root).baseline, centeredConnection, content2);
        }
        if ((root instanceof HorizontalWidgetRun) || ((root instanceof ChainRun) && ((ChainRun) root).orientation == 0)) {
            ConstraintWidget.DimensionBehaviour behaviour = root.widget.getHorizontalDimensionBehaviour();
            if (behaviour == ConstraintWidget.DimensionBehaviour.FIXED || behaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                if (!start.targets.isEmpty() && end.targets.isEmpty()) {
                    sb.append("\n");
                    sb.append(end.name());
                    sb.append(" -> ");
                    sb.append(start.name());
                    sb.append("\n");
                } else if (start.targets.isEmpty() && !end.targets.isEmpty()) {
                    sb.append("\n");
                    sb.append(start.name());
                    sb.append(" -> ");
                    sb.append(end.name());
                    sb.append("\n");
                }
            } else if (behaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && root.widget.getDimensionRatio() > 0.0f) {
                sb.append("\n");
                sb.append(root.widget.getDebugName());
                sb.append("_HORIZONTAL -> ");
                sb.append(root.widget.getDebugName());
                sb.append("_VERTICAL;\n");
            }
        } else if ((root instanceof VerticalWidgetRun) || ((root instanceof ChainRun) && ((ChainRun) root).orientation == 1)) {
            ConstraintWidget.DimensionBehaviour behaviour2 = root.widget.getVerticalDimensionBehaviour();
            if (behaviour2 == ConstraintWidget.DimensionBehaviour.FIXED || behaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                if (!start.targets.isEmpty() && end.targets.isEmpty()) {
                    sb.append("\n");
                    sb.append(end.name());
                    sb.append(" -> ");
                    sb.append(start.name());
                    sb.append("\n");
                } else if (start.targets.isEmpty() && !end.targets.isEmpty()) {
                    sb.append("\n");
                    sb.append(start.name());
                    sb.append(" -> ");
                    sb.append(end.name());
                    sb.append("\n");
                }
            } else if (behaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && root.widget.getDimensionRatio() > 0.0f) {
                sb.append("\n");
                sb.append(root.widget.getDebugName());
                sb.append("_VERTICAL -> ");
                sb.append(root.widget.getDebugName());
                sb.append("_HORIZONTAL;\n");
            }
        }
        if (root instanceof ChainRun) {
            return generateChainDisplayGraph((ChainRun) root, content2);
        }
        return sb.toString();
    }
}
