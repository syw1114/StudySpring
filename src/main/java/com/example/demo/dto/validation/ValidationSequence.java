package com.example.demo.dto.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

//순번 정해주는 것.
@GroupSequence({ValidationGroups.NotBlankGroup.class,
        ValidationGroups.SizeGroup.class,
        ValidationGroups.PatternCheckGroup.class,
        Default.class
})
public interface ValidationSequence {

}
